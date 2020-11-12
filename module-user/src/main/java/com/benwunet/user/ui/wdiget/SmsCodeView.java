package com.benwunet.user.ui.wdiget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.benwunet.base.R;
import com.benwunet.base.sms.SmsObserver;
import com.benwunet.base.utils.EmptyUtils;
import com.benwunet.base.wdiget.KeyBordUtil;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.base.wdiget.SimpleTextWatcher;

import java.util.Map;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     验证码组合控件
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class SmsCodeView extends LinearLayout implements View.OnFocusChangeListener {

    public static final int TYPE_POST_LOGIN = 0;
    private int TYPE_POST = TYPE_POST_LOGIN;
    /**
     * 根元素
     */
    private View rootView;
    /**
     * 编辑框
     */
    private EditText etSmsCode;
    /**
     * 获取验证码的按钮
     */
    private TextView
            tvSmsGetCode;
    /**
     * 检测获取焦点的空间
     */
    private TextView tvPhone, tvPwd;
    /**
     * 短信接收的观察者
     */
    private SmsObserver observer;

    /**
     * 标记是否注册短信观察者
     */
    private boolean isRegistSms;
    /**
     * 吐丝内容
     */
    private String toast = getContext().getString(R.string.res_getcode);
    private String codeNumber = "+86";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                tvSmsGetCode.setEnabled(true);
                tvSmsGetCode.setText(toast);
                return;
            }
            tvSmsGetCode.setText(msg.what + "s");
        }
    };
    /**
     * 自动填写短信验证码回调
     */
    private OnSmsCallBack mOnSmsCallBack;

    /**
     * 失去焦点监听
     */
    private OnNotFocusListener onNotFocusListener;
    private String mPhone;

    public SmsCodeView(Context context) {
        this(context, null);
    }

    public SmsCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmsCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setTextSize(float size) {
        etSmsCode.setTextSize(size);

    }

    // 双向绑定 输入框内容
    public void setText(String text) {
        if (!getText().equals(text)) {
            etSmsCode.setText(text);
        }
    }
    private String getText() {
        Editable text = etSmsCode.getText();
        if (text != null) {
            return text.toString();
        } else {
            return "";
        }

    }

    // SET 方法
    @BindingAdapter("y_change_content")
    public static void setStr(SmsCodeView cetv, String content) {
        if (cetv != null) {
            String mCurrentStr = cetv.etSmsCode.getText().toString().trim();
            if (!TextUtils.isEmpty(content)) {
                if (!content.equalsIgnoreCase(mCurrentStr)) {
                    cetv.etSmsCode.setText(content);
                    // 设置光标位置
                    cetv.etSmsCode.setSelection(content.length());
                }
            }
        }
    }

    // GET 方法
    @InverseBindingAdapter(attribute = "y_change_content", event = "contentAttrChanged")
    public static String getStr(SmsCodeView cetv) {
        return cetv.etSmsCode.getText().toString().trim();
    }

    // 监听,如果有变动就调用listener中的onChange方法
    @BindingAdapter(value = "contentAttrChanged", requireAll = false)
    public static void setChangeListener(SmsCodeView cetv, final InverseBindingListener listener) {
        cetv.etSmsCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listener.onChange();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


        /**
         * 初始化控件
         */
    private void initView() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.include_sms_code, this, true);
        etSmsCode = (EditText) findViewById(R.id.et_sms_code);
        tvSmsGetCode = (TextView) findViewById(R.id.tv_sms_getcode);
        tvSmsGetCode.setOnClickListener(new OnNoDoubleClickListener() {
            private Map<String, String> codeMap;

            @Override
            protected void onNoDoubleClick(View v) {
                if (tvPhone != null) {
                    String phone = tvPhone.getText().toString();
                    if(mPhone!=null&&mPhone.length()>0){
                        phone=mPhone;
                    }
                    if (mOnSmsCallBack != null) {
                        mOnSmsCallBack.call(TYPE_POST);
                    }
                }
            }
        });
    }

    //注册短信观察者
    public void registerSms() {
        if (isRegistSms) return;

        Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == SmsObserver.MSG_RECEIVED_CODE) {
                    String code = (String) msg.obj;
                    /**
                     * 更新UI：实现自动填写短信验证码
                     */
//                    etSmsCode.setText(code);
                    if (tvPwd != null) {
                        KeyBordUtil.reqFocus(tvPwd);
                    }
                }
            }
        };
        observer = new SmsObserver(getContext(), mHandler);
        observer.register();
        isRegistSms = true;
    }

    /**
     * 注销短信观察者
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (observer != null) {
            observer.unRegister();
            observer = null;
        }
    }

    /**
     * 设置获取验证码按钮不可点击
     */
    public void reset() {

        tvSmsGetCode.setEnabled(false);
    }

    public void startDjs() {
        reset();
        new Thread(getCodeTask).start();
    }

    public EditText getEtitTextCode() {
        return etSmsCode;
    }

    public TextView getTvSmsGetCode() {
        return tvSmsGetCode;
    }

    public String getCode() {
        return etSmsCode.getText().toString();
    }

    Runnable getCodeTask = new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 60; i >= 0; i--) {
                    handler.sendEmptyMessage(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public void setCodeTextReset(){
        handler.sendEmptyMessage(0);
    }

    public void setOnNotFocusListener(OnNotFocusListener onNotFocusListener) {
        this.onNotFocusListener = onNotFocusListener;
    }

    public void setOnSmsCallBack(OnSmsCallBack callBack) {
        this.mOnSmsCallBack = callBack;
    }

    public void setTvPwd(EditText tvPwd) {
        this.tvPwd = tvPwd;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            if (onNotFocusListener != null && tvPhone.getText().toString().length() > 6) {
                onNotFocusListener.notFocus(false);
            }
        }
    }

    public void bindBtn(final TextView btn) {
        getEtitTextCode().addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btn.setEnabled(EmptyUtils.isNotEmpty(s.toString()) && s.length() >= 4 && tvPhone.getText().length() >= 10);
            }
        });
    }

    /**
     * 自动填写短信的回调
     */
    public interface OnSmsCallBack {
        void call(int type);
    }

    /**
     * 失去焦点监听
     */
    public interface OnNotFocusListener {
        void notFocus(boolean focus);
    }

    public void setTvPhone(TextView textView) {
        this.tvPhone = textView;
    }

    public void setEvPhone(EditText textView) {

        this.tvPhone = textView;
    }

    public void setOnEditActionListener(TextView.OnEditorActionListener onEditActionListener, CharSequence label, int actionId) {
        if (null != onEditActionListener) {
            etSmsCode.setImeOptions(actionId);
            etSmsCode.setOnEditorActionListener(onEditActionListener);
            etSmsCode.setImeActionLabel(label, actionId);
        }

    }

    public void setFocusListener(OnNotFocusListener onNotFocusListener) {
        if (tvPhone != null) {
            tvPhone.setOnFocusChangeListener(this);
        }
        if (tvPwd != null) {
            tvPwd.setOnFocusChangeListener(this);
        }
        if (etSmsCode != null) {
            etSmsCode.setOnFocusChangeListener(this);
        }
        setOnNotFocusListener(onNotFocusListener);
    }

    private CodeNumberCallBack mCodeNumberCallBack;

    public void setCodeNumberCallBack(CodeNumberCallBack codeNumberCallBack) {
        mCodeNumberCallBack = codeNumberCallBack;
    }

    public static interface CodeNumberCallBack {
        String getCodeNumber();
    }
}
