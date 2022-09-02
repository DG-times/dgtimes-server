
### Commit 컨벤션
```
feat : 새로운 기능에 대한 커밋 
fix : 수정에 대한 커밋 
build : 빌드 관련 파일 수정에 대한 커밋
```

### Github flow
```
#Branch naming rules
main
항상 보호되는 안정된 브랜치

dev
새 버전 준비를 위한 개발 브랜치

hotfix/#notification_list
이슈 해결을 위한 브랜치, 기능 명을 기입하여 구분

feature/#notification_list
기능 추가/제거를 위한 브랜치, 기능명을 기입하여 구분

#Contribute method
1. Main 브랜치는 항상 안정된 빌드이자 사용자에게 서비스중인 빌드입니다.
2. 모든 feature 브랜치는 dev 브랜치에서 클론한다.
3. feature 브랜치는 기능 단위로 구분한다.
4. feature 작업이 끝나면 dev branch로 pull request 한다.
5. dev branch에서 모든 취합이 끝나면 main으로 pull request 한다.
6. merge 후 브랜치는 삭제한다.
```
