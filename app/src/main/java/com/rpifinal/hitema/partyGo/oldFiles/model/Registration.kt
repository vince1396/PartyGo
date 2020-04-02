package com.rpifinal.hitema.partyGo.oldFiles.model

class Registration {
    var uid: String? = null
    var token: String? = null

    constructor() {}
    constructor(uid: String?, token: String?) {
        this.uid = uid
        this.token = token
    }

}