# weekly-schedule-view

```
🗓️ 주간 일정을 한 눈에 볼 수 있는 View 입니다.
```

## Features
- 이전/다음 주로 이동 가능
- 스케줄 설정 및 표시
- 하루의 시작/끝 시간 설정 가능
- 각 칸을 눌렀을 때 클릭 리스너 설정 가능
- 테마 및 색상 지정 (추가 예정)

## Usage
1. settings.gradle
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' } //추가
    }
}
```

2. 의존성 추가
```groovy
dependencies {
    implementation 'com.github.jerrytrap:weekly-schedule-view:version'
}
```

## 실행 화면
<img width="270" src="https://github.com/jerrytrap/weekly-schedule-view/assets/61190129/9f8ec2e2-0a70-47a9-b70d-3cf2782bdf48"/>
