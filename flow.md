# API 계층
## Spring MVC 아키텍처
### Spring MVC
#### View, Modle, Controller, 내부 동작 흐름
## Controller
### Controller 클래스 설계 및 구조 생성
#### Controller 생성 및 설계 (@RequestMapping, @RestController)
### 핸들러 메서드
#### 핸들러메서드 생성(@RequestMapping(produces), @PostMapping, @RequestParam, @GetMapping, @PathVariable)
#### 코드 개선점 (JSON 직접 작성, @RequestParam 없애기)
### ResponseEntity 적용
#### 코드 개선(JSON수작업 -> Map객체 (produces 삭제), 리턴 값 String -> ResponseEntity(HTTP 상태 코드 추가))