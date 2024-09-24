package io.github.nvg064.xiaomicharginganimation.hook

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

@InjectYukiHookWithXposed
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            tag = "XiaomiChargingAnimation"
        }
    }

    override fun onHook() = encase {
        loadApp(name = "com.android.systemui") {
            YLog.info(msg = "Applying the hook")

            "com.miui.charge.ChargeUtils".toClass().method {
                name = "getChargeAnimationType"
            }.hookAll {
                replaceTo(any = 3)
            }

            YLog.info(msg = "Hook success.")
        }
    }
}