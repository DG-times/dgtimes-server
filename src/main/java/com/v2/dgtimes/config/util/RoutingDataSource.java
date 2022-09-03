package com.v2.dgtimes.config.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static com.v2.dgtimes.config.util.DatabaseType.*;

/*
설명 : RoutingDataSource - Transaction이 readOnly인지 여부에 따라 데이터베이스를 선택할 수 있도록 했습니다.

작성일 : 2022.09.03

마지막 수정한 사람 : 안상록

*/
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? REPLICA : SOURCE;
    }
}