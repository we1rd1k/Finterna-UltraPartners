package com.ultrapartners.ui.tests.utils

import com.codeborne.selenide.Condition

var clickable: Condition = Condition.and("Element is clickable", Condition.visible, Condition.enabled)