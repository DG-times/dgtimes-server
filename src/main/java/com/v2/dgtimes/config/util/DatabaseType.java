package com.v2.dgtimes.config.util;

/*
설명 : Source와 Replica 데이터베이스에 대한 타입을 상수로 정의하는 클래스를 생성합니다.

작성일 : 2022.09.03

마지막 수정한 사람 : 안상록

*/
public enum DatabaseType {

    SOURCE("SOURCE"), REPLICA("REPLICA");

    private String type;

    DatabaseType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}