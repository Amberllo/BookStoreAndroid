package com.bookshop.view;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bookshop.R;

public class FormTextInputView extends LinearLayout {

    public static int DefaultValue_MaxLength = 999;
    private EditText edt_content;
    private TextView tv_title;
    private TextView tv_errormsg;
    private View view_require;

    //必填
    private boolean require;
    //只读
    private boolean readonly;
    //标题
    private String title;
    //输入框
    private String hint;
    //最大长度
    private int maxLength = DefaultValue_MaxLength;

    private FormTextInputView(Context context, Builder builder) {
        super(context);
        this.require = builder.require;
        this.readonly = builder.readonly;
        this.title = builder.title;
        this.hint = builder.hint;
        this.maxLength = builder.maxLength;
        init(context);

    }

    public FormTextInputView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_textinput_view,this,true);
        tv_title = (TextView)findViewById(R.id.formview_textinput_title);
        tv_errormsg = (TextView)findViewById(R.id.formview_textinput_errormsg);
        view_require = findViewById(R.id.formview_textinput_require);
        edt_content = (EditText)findViewById(R.id.formview_textinput_content);

        setRequire(this.require);
        setReadonly(this.readonly);
        setTitle(this.title);
        setHint(this.hint);
        setMaxLength(this.maxLength);
        tv_errormsg.setVisibility(View.GONE);
        edt_content.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edt_content.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edt_content.getWindowToken(), 0) ;

                }
                return false;
            }
        });
    }

    public void setRequire(boolean require) {
        this.require = require;
        view_require.setVisibility(require?View.VISIBLE:View.INVISIBLE);
    }

    public void setReadonly(boolean readonly){
        this.readonly = readonly;
        edt_content.setEnabled(!readonly);

        String hint = this.hint;
        if(readonly){
            setHint("");
        }else{
            setHint(hint);
        }
    }

    public void setTitle(String title) {
        this.title = title;
        tv_title.setText(TextUtils.isEmpty(title)?"":title);
    }

    public void setHint(String hint) {
        this.hint = hint;
        edt_content.setHint(TextUtils.isEmpty(hint)?"":hint);
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        edt_content.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)}); //即限定最大输入字符数
    }

    public String getData() {
        String value = edt_content.getText().toString();
        return value;
    }

    public void refresh(String value) {
        edt_content.setText(value);
    }

    public boolean onValidate() {
        boolean isEmpty = TextUtils.isEmpty(edt_content.getText().toString());
        if(require){
            tv_errormsg.setVisibility(isEmpty?View.VISIBLE:View.GONE);
            return !isEmpty;//非空为合法
        }else{
            //非必填，校验直接通过
            return true;
        }
    }

    public void addTextWatcher(TextWatcher textWatcher) {
        edt_content.addTextChangedListener(textWatcher);
    }

    public void removeTextWatcher(TextWatcher watcher) {
        edt_content.removeTextChangedListener(watcher);
    }

    public void setInputType(int type) {
        edt_content.setInputType(type);
    }

    public static class Builder{

        private Context context;
        public String title;
        public boolean require;
        public boolean readonly;
        public String hint;
        public int maxLength = DefaultValue_MaxLength;
        public boolean isArea = false;

        public Builder(Context context){
            this.context = context;
        }

        public Builder setRequire(boolean require) {
            this.require = require;
            return this;
        }

        public Builder setReadonly(boolean readonly){
            this.readonly = readonly;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setHint(String hint) {
            this.hint = TextUtils.isEmpty(hint)?"请输入":hint;
            return this;
        }

        public Builder setMaxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public Builder setIsArea(boolean isArea){
            this.isArea = isArea;
            return this;
        }

        public FormTextInputView create(){
            return new FormTextInputView(context,this);
        }

    }

}
