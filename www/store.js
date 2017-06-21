var store= {
    exec:function(action,args,succCallback,errorCallback){
        cordova.exec(
            succCallback,
            errorCallback,
             "ExtentionStore",
             action,
             [args]
        )
    }
}

module.exports =store;