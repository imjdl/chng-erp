/**
 * Created by liuyandong on 2017/6/28.
 */
$.extend($.fn.validatebox.defaults.rules, {
    maxLength: {
        validator: function (value, params) {
            if (!value) {
                return false;
            } else {
                return value.length <= params[0];
            }
        },
        message: ""
    },
    originalPassword: {
        validator: function (value, params) {
            if (!value) {
                return false;
            } else {
                return value.length >= params[0] && value.length <= params[1];
            }
        },
        message: "原始密码长度必须介于{0}到{1}个字符之间！"
    },
    password: {
        validator: function (value, params) {
            if (!value) {
                return false;
            } else {
                return value.length >= params[0] && value.length <= params[1];
            }
        },
        message: "新密码长度必须介于{0}到{1}个字符之间！"
    },
    confirmPassword: {
        validator: function (value, params) {
            console.log(value);
            var elementType = params[1];
            var elementId = params[0];
            if (elementType == "input") {
                return value == $("#" + elementId).val();
            } else if (elementType == "textbox") {
                return value == $("#" + elementId).textbox("getValue");
            }
        },
        message: "确认密码与新密码不一致！"
    },
    userName: {
        validator: function (value, params) {
            if (!value) {
                return false;
            } else {
                return value.length <= params[0];
            }
        },
        message: "姓名长度不能超过{0}个字符！"
    },
    mobile: {
        validator: function (value) {
            if (!value) {
                return false;
            } else {
                return /^1[0-9]{10}$/.test(value);
            }
        },
        message: "手机号码格式不正确！"
    },
    email: {
        validator: function (value) {
            if (!value) {
                return false;
            } else {
                return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(value);
            }
        },
        message: "邮箱格式不正确！"
    },
    twoDecimalPlaces: {
        validator: function (value, params) {
            if (!value) {
                return false;
            }
            if (isNaN(value)) {
                return false;
            }
            var max = params[0];
            if (value > max) {
                return false;
            } else {
                var reg = new RegExp("^[1-9]+(.[0-9]{1," + params[1] + "})?$");
                return reg.test(value);
            }
        },
        message: ""
    },
    integer: {
        validator: function (value, params) {
            if (!value) {
                return false;
            }
            if (isNaN(value)) {
                return false;
            }
            var max = params[0];
            if (value > max) {
                return false;
            } else {
                return /^[1-9]+[0-9]$/.test(value);
            }
        },
        message: ""
    }
});