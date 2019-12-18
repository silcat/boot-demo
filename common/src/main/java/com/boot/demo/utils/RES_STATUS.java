package com.boot.demo.utils;

/**
 * RES_STATUS
 *
 * @author Wang Zhao
 * @date 2015年11月23日 下午4:38:40
 */
public enum RES_STATUS {
    // 失败
    FAILED(-1, "未知错误"),

    SUCCESS(0, "SUCCESS"),

    SERVICE_OFF(2, "非常抱歉，今晚20:00至明日08:00，系统升级维护"),


    SERVER_UNKONW_ERROR(500, "服务器开小差了,请稍后再试"),
    GATEWAY_ERROR(1500, "gateway捕获异常"),

    REMOTE_SERVER_ERROR(502, "远程服务调用失败"),

    MISS_PARAM(400, "参数异常"),

    DB_ERROR(10000, "DB操作失败"),

    PARAM_NOT_EXIST(1002, "必填字段不存在"),

    SIGN_VALIDATE_ERROR(1100, "验签失败"),
    FILE_IO_ERROR(1101, "文件操作异常"),
    THIRD_PARTY_ERROR(1102, "服务器开小差了，请稍后重试"),
    PARAM_VALUE_ERROR(1104, "参数错误"),
    REQUEST_ERROR(1105, "错误请求"),
    FEIGN_REQUEST_ERROR(1106, "FEIGN请求异常"),
    ENUM_ERROR(1107, "enum值错误"),
    DATA_ERROR(1108, "数据异常"),

    // 账户相关
    ACCOUNT_NOT_EXIST(1301, "该账号尚未注册"),
    ACCOUNT_MOBILE_NOT_EXIST(1300, "该手机号尚未注册"),
    ACCOUNT_PASSWD_NOT_MATCH(1302, "密码设置不符合规则"),
    ACCOUNT_PASSWD_ERROR(1303, "密码错误"),
    ACCOUNT_PASSWD_FAILED(1408, "修改密码失败"),
    ACCOUNT_PASSWD_WRITE_ERROR(1409, "设置密码失败"),
    ACCOUNT_VERIFY_CODE_SEND_LIMIT(1304, "验证码发送过于频繁，请1分钟后再试"),
    ACCOUNT_MOBILE_UNVERIFIED(1305, "手机未认证"),
    ACCOUNT_VERIFY_CODE_INVALID(1306, "验证码失效,请重新获取"),
    ACCOUNT_VERIFY_CODE_ERROR(1307, "验证码错误"),
    ACCOUNT_EXIST(1308, "账户已存在"),
    ACCOUNT_NO_PERMISSION(1310, "无权限访问此接口"),
    ACCOUNT_SESSION_INVALID(1311, "SESSION 无效"),
    ACCOUNT_SESSION_MISS(1312, "SESSION 不存在"),
    ACCOUNT_SESSION_WRITE_ERROR(1410, "SESSION设置失败"),
    ACCOUNT_LOGGED_ON_OTHER_DEVICE(1313, "已在其他设备上登录"),
    ACCOUNT_OPERA_LOGIN_FAIL(1314, "运营商登录验证失败"),
    ACCOUNT_MOBILE_NOT_SAME(1315, "所填电话与账户绑定电话不符"),
    ACCOUNT_OPERA_VERIFY_FAIL(1316, "运营商短信验证失败"),
    ACCOUNT_CHANGE_PWD_EQUAL(1317, "旧密码与新密码相同"),
    ACCOUNT_VERIFY_CODE_OVER_LIMIT(1318, "验证码验证次数达到上限"),
    ACCOUNT_DEVICE_TOKEN_NOT_MATCH(1319, "设备号不匹配"),
    ACCOUNT_LOAN_NOT_SUPPORT_AREA(1320, "借款服务暂不支持该地区"),
    ACCOUNT_BEFORE_NOT_SET_PASSWORD(1321, "您未设置密码，请返回用短信验证码登录"),
    ACCOUNT_ACC_BINK_ERROR(1400, "短信验证失败、过期或有误"),
    ACCOUNT_ACC_NO_SMS(1401, "请先获取验证码"),
    ACCOUNT_SORRY_CAN_NOT_REGISTER(1402, "抱歉，您暂时无法注册人人贷借款"),
    ACCOUNT_PLATFORM_NOT_MATCH(1404, "您已成为App用户，请在人人贷借款App完成后续操作"),
    ACCOUNT_VERIFY_CODE_SEND_FAIL(1403, "验证码发送失败，请稍后再试"),
    ACCOUNT_VERIFY_CODE_CHECK_FAIL(1405, "验证码验证失败"),
    ACCOUNT_REGISTER_FAILED(1406, "注册失败,清重新注册或联系管理员"),
    ACCOUNT_LOGIN_NOT_SUPPORT(1407, "登录失败,不支持当前登录模式"),


    //系统配置相关
    SYSCONFIG_KEY_NON_EXIST(2001, "KEY值不存在"),

    // app
    APP_NOT_EXIST(3001, "app信息不存在!"),

    //文件上传
    FILE_UPLOAD_CONTENT_TOOBIG(4001, "文件内容太大"),
    FILE_UPLOAD_CONTENT_READ_FAIL(4002, "文件内容读取失败"),
    FILE_UPLOAD_NOT_FOUND(4003, "文件没找到"),

    FACE_VERIFY_ERROR(5000, "照片对比失败"),
    FACE_LOAN_FLOW_ERROR(5001, "LoanFlow调用失败"),
    FACE_IDCARD_OCR_ERROR(5002, "OCR失败"),
    FACE_RESULT_ERROR(5003, "人脸识别Result解析错误"),
    FACE_VERIFY_ERROR_TOOMUCH(5004, "失败次数太多"),
    FACE_VERIFY_ERROR_ID_NAME_FAIL(5005, "身份证或姓名错误,无法获取网纹照片"),
    FACE_VERIFY_ERROR_ID_FOTO_FAIL(5006, "用户网纹照片库无或无法解析"),


    //PUSH
    PUSH_TOKEN_ERROR(5101, "无有效token"),
    PUSH_STRATEGY_ERROR(5102, "PUSH规则错误"),
    PUSH_ERROR(5103, "PUSH失败"),

    //SMS
    SMS_STRATEGY_ERROR(5201, "SMS规则错误"),


    FLOW_PRODUCT_EXIST(9001, "产品名称已存在"),
    FLOW_PRODUCT_DB_ERROR(9002, "产品数据库操作错误!"),
    FLOW_FLOW_DB_ERROR(9003, "流程数据库操作错误!"),
    FLOW_NODE_EXIST(9004, "流程节点已存在"),
    FLOW_NODE_NOT_EXIST(9005, "流程节点不存在"),
    FLOW_NODE_DB_ERROR(9006, "流程节点数据库错误"),

    FLOW_APPEND_NOT_COMPLETE(9007, "追加贷流程未完成"),
    FLOW_CREDIT_TYPE_ERROR(9008, "用户状态异常!"),
    FLOW_NOT_EXIST(9009, "用户流程不存在!"),
    FLOW_REDIS_LOCK_FAILED(9010, "该进件正在处理请稍候重试!"),
    FLOW_REDIS_LOCK_UID_FAILED(9015, "用户正在创建流程!"),
    FLOW_STATUS_WRONG(9011, "流程状态错误!存在未完成流程"),
    FLOW_AUTH_FLOW_EXIST(9012, "用户流程已存在!"),
    FLOW_PROD_FLOW_EXIST(9013, "用户在当前产品下已存在对应流程!"),
    FLOW_APPEND_FLOW_EXPIRED(9014, "追加贷不存在身份证过期的流程!"),
    FLOW_NODE_ERROR(9015, "上报流程模块不正确!"),
    FLOW_STATE_ERROR(9016, "反馈loanflow状态错误!"),
    FLOW_RESTATEMENT_EXIST(9017, "余额再提现流程已经存在!"),
    FLOW_RESTATEMENT_FAILED(9018, "余额再提现流程非法无法创建!"),
    FLOW_RESTATEMENT_NOT_EXIST(9019, "余额再提现流程不存在!"),
    FLOW_RESTATEMENT_ERROR(9020, "余额再提现流程异常!"),
    FLOW_AUTH_FLOW_LOG_NOT_EXIST(9021, "查询用户进件历史失败!"),
    FLOW_AUTH_FLOW_NOT_REPAYING(9022, "用户流程未处于还款中!"),
    FLOW_AUTH_FLOW_MORE_REPAYING(9023, "用户存在多笔流程处于还款中!"),
    FLOW_QUOTA_FLOW_NOT_READY(9024, "提额流程未完成!"),


    // data-api
    DATA_API_BATTERY_ERROR(10001, "添加或者更新用户电量信息失败!"),
    DATA_API_UMENG_TOKEN_ERROR(10002, "添加或更新umeng token失败!"),
    DATA_API_TONGDUN_TOKEN_ERROR(10003, "添加或更新同盾 token失败!"),
    DATA_API_DEVICE_TOKEN_ERROR(10004, "添加或更新device token失败!"),
    DATA_API_VERSION_LATEST(10005, "已是最新版本"),
    DATA_API_FEEDBACK_LIMIT(10006, "您已达到反馈次数限制，请明天再试，如有问题可联系客服"),
    DATA_API_UPDATE_PERSONAL_ERROR(10007, "更新个人信息失败!"),
    DATA_API_AMOUNT_ERROR(10008, "预估额度计算错误!"),
    DATA_API_EMERGENCY_NAME_ERROR(10009, "紧急联系人姓名错误!"),

    // finance
    FINANCE_BANK_CODE_ERROR(11001, "bank_code参数错误!"),
    FINANCE_ACC_NO_SMS(11002, "请先获取验证码"),
    FINANCE_ACC_BINK_ERROR(11003, "短信验证失败、过期或有误"),
    FINANCE_BANK_FAIL(11004, "银行卡已绑定"),
    FINANCE_USER_NO_CARD(11005, "当前用户没有绑定银行卡"),
    FINANCE_USER_ONE_CARD(11006, "用户必须保留一张还款卡"),
    FINANCE_CARD_NOT_BIND(11007, "银行卡未绑定"),
    FINANCE_CARD_USED(11008, "银行卡正在使用中,请换卡后再进行删除"),

    FINANCE_CMBC_ERROR(11009, "查询民生银行信息错误!"),
    FINANCE_DB_ERROR(11010, "数据写库失败!"),
    FINANCE_DATA_API_ERROR(11011, "查询data-api错误!"),
    FINANCE_CMBC_CALLBACK_ERROR(11012, "民生银行回调错误"),
    FINANCE_USRID_OVER_LIMIT(11013, "民生银行usrId超过最大限制"),
    FINANCE_LOAN_TYPE_ERROR(11014, "借款类型错误"),
    FINANCE_PUSH_ACCOUNTING_FAILED(11015, "推送帐务失败"),
    FINANCE_AUTHFLOW_ERROR(11016, "更新流程异常"),
    FINANCE_LOAN_NOT_EXIST(11017, "loan信息不存在"),
    FINANCE_BILL_PERIOD_ERROR(11018, "账单期数计算错误"),

    FINANCE_ACCOUNTING_ERROR(11019, "帐务查询错误"),

    FINANCE_CMBC_CANNOT_UNBIND(11020, "暂时无法绑卡"),
    FINANCE_CMBC_BIND_CARD_BEFORE_WITHDRAW(11021, "请先绑定收款银行卡"),
    FINANCE_ACCOUNTING_CARD_EXPIRED(11022, "银行卡已过期"),

    FINANCE_CARD_UNBIND_ONE_ERROR(11023, "用户仅绑定了一张银行卡"),
    FINANCE_CARD_NO_ERROR(11024, "您的银行卡号有误"),
    FINANCE_CARD_BANK_NAME_ERROR(11025, "银行卡号与发卡行不一致，请核实"),
    FINANCE_CARD_UNBIND_FORBIDDEN(11026, "不允许解绑卡"),
    FINANCE_CARD_UNBIND_ACCOUNTING_ERROR(11027, "账务解绑卡失败"),
    FINANCE_CARD_ID_ERROR(11028, "当前用户未绑定此银行卡"),
    FINANCE_CARD_ID_REPAY_ERROR(11029, "您输入的验证码已失效，请重新获取"),
    FINANCE_CARD_UNBIND_REPAY_DAY(11030, "还款日当日暂无法解绑"),
    FINANCE_CARD_UNBIND_REPAYING(11031, "您有借款正在还款中，暂无法解绑"),
    FINANCE_CARD_UNBIND_REQUEST_ALL(11032, "为保证用户至少有一张绑定卡，此卡暂不支持解绑"),
    FINANCE_CARD_UNBIND_NO_ERROR(11033, "解绑卡卡号错误"),
    FINANCE_CARD_BANK_TYPE_ERROR(11034, "仅支持绑定借记卡/储蓄卡，请换卡重试"),

    //余额再提现
    FINANCE_LOAN_OVERDUE_CANNOT_WITHDRAW(11041, "您有逾期未还账单，暂无法提现"),
    FINANCE_LOAN_CONTINUE_WITHDRAW_ERROR(11042, "申请余额再提现异常"),
    FINANCE_LOAN_BORROW_TYPE_ERROR(11043, "提现类型异常"),
    FINANCE_LOAN_CREDIT_EXPIRE(11044, "抱歉，您的额度已过期，请重新申请"),
    FINANCE_LOAN_CONTINUE_PUBLIC_DEBT_FAIL(11045, "余额再提现共债锁定失败"),
    FINANCE_LOAN_CONTINUE_PUBLIC_DEBT_LOCKED(11046, "余额再提现共债重复锁定"),
    FINANCE_LOAN_CONTINUE_STATUS_ERROR(11047, "余额再提现异常"),
    FINANCE_PUBLIC_DEBT_FAIL(11048, "共债锁定失败"),

    FINANCE_CMBC_DECRYPT_ERROR(11049, "民生解密失败"),
    FINANCE_PERIOD_PAYOFF(11050, "当前应还账单已结清"),

    // 追加贷
    FLOW_APPEND_LOAN_NOT_EXIST(11061, "追加贷loan信息不存在"),
    FLOW_APPEND_LOAN_DAY_NO_ENOUGH(11062, "距您最近还款日不足3日，请3日后再试"),
    FLOW_APPEND_LOAN_CREDIT_EXPIRE(11063, "抱歉，您的额度已过期，请重新申请"),
    FLOW_APPEND_LOAN_APPLY_EXPIRE(11064, "追加贷申请资格已过期，暂无法继续申请"),
    FLOW_APPEND_EXIST_OVERDUE(11065, "您当期账单处于逾期，请还款后再提交新的申请"),

    //public-debt

    EXIST_NOT_RELATED_TID(12001, "存在未使用的tid"),
    PUBLIC_DEBT_EXPIRE(12002, "公债过期"),
    PUBLIC_DEBT_PHASE_ERROR(12003, "借款期数错误"),
    PUBLIC_DEBT_AMOUNT_ERROR(12004, "借款金额错误"),
    PUBLIC_DEBT_NOT_EXIST(12005, "public-debt信息不存在"),


    //合同
    CONTRACT_NOT_CREATED(13001, "合同尚未生成，请稍后再试"),
    CONTRACT_TYPE_ERROR(13002, "合同类型错误"),
    CONTRACT_LEND_DAY_ERROR(13003, "合同还款日范围错误"),
    CONTRACT_PATH_ERROR(13004, "合同路径错误"),
    CONTRACT_PATH_NOT_EXIST(13005, "合同路径不存在");

    RES_STATUS(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public final int code;
    public final String msg;

    public static RES_STATUS findStatusByCode(int code) {
        for (RES_STATUS status : RES_STATUS.values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }

    /**
     * success:Y
     * not success:N
     *
     * @param code
     * @return
     */
    public static boolean isSuccess(int code) {
        return code == RES_STATUS.SUCCESS.code;
    }


    public boolean isSuccess() {
        return code == RES_STATUS.SUCCESS.code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
