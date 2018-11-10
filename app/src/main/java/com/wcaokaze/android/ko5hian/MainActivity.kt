package com.wcaokaze.android.ko5hian

import android.app.*
import android.os.Bundle
import android.view.Gravity
import ko5hian.*
import kotlin.contracts.ExperimentalContracts

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @UseExperimental(ExperimentalContracts::class)
        val view = ko5hian(this) {
            frameLayout {
                textView {
                    layout.width  = MATCH_PARENT
                    layout.height = WRAP_CONTENT
                    layout.gravity = Gravity.END
                    view.text = "hello"
                }
            }
        }

        setContentView(view)
    }
}
