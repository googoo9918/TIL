## MapStruct 사용 중 발생한 에러
- 다음과 같은 에러를 마주하였다.

```
C:\Users\DBInc\Project\visit\src\main\java\com\inc\visit\domain\visit\mapper\VisitMapper.java:18: warning: Unmapped target property: "mangerId".
    VisitWriteVo.VisitHistoryWriteVo visitRequestToVisitHistoryWriteVo(VisitRequestDto.VisitRequest visitRequest);
                                     ^
C:\Users\DBInc\Project\visit\src\main\java\com\inc\visit\domain\visit\mapper\VisitMapper.java:22: warning: Unmapped target properties: "cDiskCapacity, dDiskCapacity, eDiskCapacity".
    VisitWriteVo.VisitItemWriteVo visitRequestToVisitItemWriteVo(VisitRequestDto.VisitRequest visitRequest);
```

- 이때 mapper가 cDiskCapacity, dDiskCapacity, eDiskCapacity 등을 제대로 인식하지 못하였고
    - 필드명 에러는 아님을 확인하였음
    - build/generated/sources/annotationProcessor/java/visitMapperImpl 을 확인해 봤을 때,
    - get 메서드를 사용하지 못하고 null로만 지정하는 것을 확인할 수 있었다.
        - 예를 들면 다음과 같다.

```java
@Override
    public VisitWriteVo.VisitItemWriteVo visitRequestToVisitItemWriteVo(VisitRequestDto.VisitRequest visitRequest) {
        if ( visitRequest == null ) {
            return null;
        }

        String checkTransferItem = null;
        String transferItem = null;
        String transferReason = null;
        String otherDiskCapacity = null;

        checkTransferItem = visitRequest.getCheckTransferItem();
        transferItem = visitRequest.getTransferItem();
        transferReason = visitRequest.getTransferReason();
        otherDiskCapacity = visitRequest.getOtherDiskCapacity();


        String cDiskCapacity = null;
        String dDiskCapacity = null;
        String eDiskCapacity = null;
        
        VisitWriteVo.VisitItemWriteVo visitItemWriteVo = new VisitWriteVo.VisitItemWriteVo( checkTransferItem, transferItem, transferReason, cDiskCapacity, dDiskCapacity, eDiskCapacity, otherDiskCapacity );

        return visitItemWriteVo;
    }
```

- 문제를 생각해봤을 때, `getCDiskCapacity()`와 같은 형식이 문제가 되는 것 같았다.
    - mapper 내부 구조가 어떻게 되어 있는지는 정확히 모르겠으나, 첫 번째 문자 다음에 바로 대문자가 오면, 제대로 필드명을 인식하지 못하는 듯
    - 따라서 다음과 같은 시도를 통해 해결할 수 있었다.

```java
@Mapping(source = "CDiskCapacity", target = "cDiskCapacity")
    @Mapping(source = "DDiskCapacity", target = "dDiskCapacity")
    @Mapping(source = "EDiskCapacity", target = "eDiskCapacity")
    VisitWriteVo.VisitItemWriteVo visitRequestToVisitItemWriteVo(VisitRequestDto.VisitRequest visitRequest);
```

- 임시방편에 불과한 코드일 수 있으나, 변수명을 아예 수정하지 않는 이상 어쩔 수 없는 방법인 듯
- 어쩌면 contribute를 할 수 있는 좋은 기회가 될 수 있지 않을까? 싶기도 하다.
    - 혹은 이미 해결된 문제인데 나의 버전이 낮아 남아있는 문제일지도.