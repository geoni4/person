# AOP
- 유효성 검사 같은 종단이 아닌 횡단에 필터를 거는 것이라고 생각하면 됨
  - 핵심 기능
    - 유저 정보 가져오기
  - 공통 기능
    - 유효성 검사
    - 세션 체크
    - 로그 남기기
  - 필터 + 리플렉션을 통해 앞과 뒤를 전부 제어하는 것
  
# Advice, Pointcut
  - 공통 기능 Aspect(기능을 Advice)
  - pointcut은 해당 jointpoint에 advice를 집어넣는 행위


# @Before, @After, @Around
  - ProceedingJoinPoint 로 리플렉션을 대신할 수 있으나 전처리만 할 경우에는 사용할 수 없다. @Around 사용 시에만 사용가능
  - Dto에 @NotNull 등 어노테이션을 적용한 후에 이 Dto를 받는 컨트롤러에서 @Valid어노테이션을 적용하고 BindingResult 타입의 변수를 사용한다. 
  - 각 어노테이션 value로 ("execution ~~~")의 정규식을 넣어주어야 한다. 검색해서 활용하도록

#