package com.boot.demo.common;


public interface FundConstants {
    /**
     * 业务方编码
     */
    String PARTNER_CODE_RRD = "RRD";

    /**
     * 通用账务响应码
     */
    interface RequestStatus{
        String FUND_SUCCESS_STATUS = "0000";
        String FUND_FAIlED_STATUS = "0001";
        //自定义
        String FUND_FAIlED_RETRY_STATUS = "-99";
        String FUND_FAIlED_WAIT_NOTIFY_STATUS = "-98";
    }

    /**
     * 进件
     */
    interface FundLoan{
        String LOAN_TYPE_CASH_BORROW = "CASH_BORROW";
        /**
         * 进件阶段：APPLICATION：申请 LOAN：借款
         */
        String STAGE_APPLICATION = "APPLICATION";
        String STAGE_LOAN = "LOAN";
        /**
         * 还款周期的单位
         */
        String UNIT_MONTH = "MONTH";
        /**
         * 支付渠道
         */
        String PAY_CHANNEL_FUND = "FUND";

        /**
         * 1:收款；2：还款
         */
        int USER_CARD_USAGE_APPLICATION = 1;
        int USER_CARD_USAGE_LOAN = 2;

        /**
         * 1：储蓄卡
         */
        int BANK_CARD_TYPE = 1;
        String REPAYMENTMODE = "DEBX";
        /**
         * :1:收款；2：还款
         */
        int USAGE_REPAY = 2;
        int USAGE_WITHDRAW = 1;
    }


}
