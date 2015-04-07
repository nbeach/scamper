package com.nicholasbeach.scamper.persistence;

import com.nicholasbeach.scamper.domain.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TransactionMapper extends ResourceMapper<Transaction> {

    public List<Transaction> retrieveInDateRange(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}
