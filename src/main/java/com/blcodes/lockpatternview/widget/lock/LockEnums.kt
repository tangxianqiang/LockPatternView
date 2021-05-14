package com.blcodes.lockpatternview.widget.lock

import com.blcodes.lockpatternview.R

/**
 * The states of the left footer button.
 */
enum class LeftButtonMode
/**
 * @param text The displayed text for this mode.
 * @param enabled Whether the button should be enabled.
 */(val text: Int, val enabled: Boolean) {
    Retry(R.string.lockpattern_retry_button_text, true), RetryDisabled(R.string.lockpattern_retry_button_text, false), Gone(-1, false);
}

/**
 * The states of the right button.
 */
enum class RightButtonMode
/**
 * @param text The displayed text for this mode.
 * @param enabled Whether the button should be enabled.
 */(val text: Int, val enabled: Boolean) {
    Continue(R.string.next_label, true), ContinueDisabled(R.string.next_label, false), Confirm(R.string.lockpattern_confirm_button_text, true), ConfirmDisabled(R.string.lockpattern_confirm_button_text, false), Ok(android.R.string.ok, true);
}

/**
 * Keep track internally of where the user is in choosing a pattern.
 */
enum class Stage(val messageForBiometrics: Int, val message: Int, val headerMessage: Int,
                 leftMode: LeftButtonMode,
                 rightMode: RightButtonMode,
                 footerMessage: Int, patternEnabled: Boolean) {
    Introduction(
            R.string.lock_settings_picker_biometrics_added_security_message,
            R.string.lockpassword_choose_your_pattern_message,
            R.string.lockpattern_recording_intro_header,
        LeftButtonMode.Gone, RightButtonMode.ContinueDisabled,
            -1, true),
    HelpScreen(
            -1, -1, R.string.lockpattern_settings_help_how_to_record,
        LeftButtonMode.Gone, RightButtonMode.Ok, -1, false),
    ChoiceTooShort(
            R.string.lock_settings_picker_biometrics_added_security_message,
            R.string.lockpassword_choose_your_pattern_message,
            R.string.lockpattern_recording_incorrect_too_short,
        LeftButtonMode.Retry, RightButtonMode.ContinueDisabled,
            -1, true),
    FirstChoiceValid(
            R.string.lock_settings_picker_biometrics_added_security_message,
            R.string.lockpassword_choose_your_pattern_message,
            R.string.lockpattern_pattern_entered_header,
        LeftButtonMode.Retry, RightButtonMode.Continue, -1, false),
    NeedToConfirm(
            -1, -1, R.string.lockpattern_need_to_confirm,
        LeftButtonMode.Gone, RightButtonMode.ConfirmDisabled,
            -1, true),
    ConfirmWrong(
            -1, -1, R.string.lockpattern_need_to_unlock_wrong,
        LeftButtonMode.Gone, RightButtonMode.ConfirmDisabled,
            -1, true),
    ChoiceConfirmed(
            -1, -1, R.string.lockpattern_pattern_confirmed_header,
        LeftButtonMode.Gone, RightButtonMode.Confirm, -1, false);

    var leftMode: LeftButtonMode = LeftButtonMode.Gone
    var rightMode: RightButtonMode = RightButtonMode.Confirm
    val footerMessage: Int
    val patternEnabled: Boolean

    /**
     * @param messageForBiometrics The message displayed at the top, above header for
     * fingerprint flow.
     * @param message The message displayed at the top.
     * @param headerMessage The message displayed at the top.
     * @param leftMode The mode of the left button.
     * @param rightMode The mode of the right button.
     * @param footerMessage The footer message.
     * @param patternEnabled Whether the pattern widget is enabled.
     */
    init {
        this.leftMode = leftMode
        this.rightMode = rightMode
        this.footerMessage = footerMessage
        this.patternEnabled = patternEnabled
    }
}
