package com.jc.app.blog.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.button.MaterialButton
import com.jc.app.blog.R

class ToggleCustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var buttonOnClickListener: (buttonOption: ButtonOption) -> Unit = {}

    private val buttonOptionOne: MaterialButton
    private val buttonOptionTwo: MaterialButton
    private var buttonOptionOneText: String = ""
    private var buttonOptionTwoText: String = ""
    var buttonSelected: Int = 1

    init {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.view_toggle_custom, this, true)

        buttonOptionOne = view.findViewById(R.id.btnOptionOne)
        buttonOptionTwo = view.findViewById(R.id.btnOptionTwo)
        buttonOptionOne.setOnClickListener(this)
        buttonOptionTwo.setOnClickListener(this)

        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ToggleThreeButtonsCustomView,
            0,
            0
        )

        buttonOptionOneText =
            attributes.getString(R.styleable.ToggleThreeButtonsCustomView_ttb_button_option_one_text)
                ?: ""
        buttonOptionTwoText =
            attributes.getString(R.styleable.ToggleThreeButtonsCustomView_ttb_button_option_two_text)
                ?: ""

        buttonSelected =
            attributes.getInteger(R.styleable.ToggleThreeButtonsCustomView_ttb_button_selected, 1)

        buttonOptionOne.text = buttonOptionOneText
        buttonOptionTwo.text = buttonOptionTwoText
        setButtonSelectedByDefault(buttonSelected)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnOptionOne -> {
                buttonOnClickListener.invoke(ButtonOption.ONE)
                setCheckedStateOfButtons(optionOne = true)
            }
            R.id.btnOptionTwo -> {
                buttonOnClickListener.invoke(ButtonOption.TWO)
                setCheckedStateOfButtons(optionTwo = true)
            }
        }
    }

    private fun setButtonSelectedByDefault(buttonSelected: Int){
        when (buttonSelected) {
            1 -> buttonOptionOne.isChecked = true
            2 -> buttonOptionTwo.isChecked = true
        }
    }

    private fun setCheckedStateOfButtons(
        optionOne: Boolean = false,
        optionTwo: Boolean = false,
    ) {
        this.buttonOptionOne.isChecked = optionOne
        this.buttonOptionTwo.isChecked = optionTwo
    }

    fun buttonsOnClickListener(buttonOnClickListener: (buttonOption: ButtonOption) -> Unit) {
        this.buttonOnClickListener = buttonOnClickListener
    }

    enum class ButtonOption{
        ONE, TWO
    }

}