package com.boot.demo.remote;


import com.boot.demo.model.server.RemoteResult;
import com.boot.demo.model.server.RemoteVo;

public interface IRemoteService {

    RemoteResult<RemoteVo> remote();

}
