//�ַ���֤      
jQuery.validator.addMethod("stringCheck", function(value, element) {      
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);      
}, "ֻ�ܰ��������֡�Ӣ����ĸ�����ֺ��»���");  
 
//�����������ֽ�      
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {      
    var length = value.length;      
    for(var i = 0; i < value.length; i++){      
        if(value.charCodeAt(i) > 127){      
        length++;      
        }      
    }      
    return this.optional(element) || ( length >= param[0] && length <= param[1] );      
}, $.validator.format("���ȱ�����ڵ���{0}�ֽ�С�ڵ���{1}�ֽ�(һ�����������ֽ�)!")); 

jQuery.validator.addMethod("compareDate", function(value, element, param) {    
    return parseFloat($(param).val().replace(/-/g, "").replace(/\//,""))<=parseFloat(value.replace(/-/g, "").replace(/\//,""));
},"�������ڱ�����ڿ�ʼ����!"); 

//���֤������֤      
jQuery.validator.addMethod("isIdCardNo", function(value, element) {      
    return this.optional(element) || isIdCardNo(value);      
}, "����ȷ�����������֤����");
    
//�ֻ�������֤      
jQuery.validator.addMethod("isMobile", function(value, element) {      
    var length = value.length;  
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1}))+\d{8})$/;  
    return this.optional(element) || (length == 11 && mobile.test(value));      
}, "����ȷ��д�����ֻ�����");      
    
//�绰������֤      
jQuery.validator.addMethod("isTel", function(value, element) {      
    var tel = /^\d{3,4}-?\d{7,9}$/;    //�绰�����ʽ010-12345678  
    return this.optional(element) || (tel.test(value));      
}, "����ȷ��д���ĵ绰����");  
 
//��ϵ�绰(�ֻ�/�绰�Կ�)��֤  
jQuery.validator.addMethod("isPhone", function(value,element) {  
    var length = value.length;  
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;  
    var tel = /^\d{3,4}-?\d{7,9}$/;  
    return this.optional(element) || (tel.test(value) || mobile.test(value));  
 
}, "����ȷ��д������ϵ�绰");  
    
//����������֤      
jQuery.validator.addMethod("isZipCode", function(value, element) {      
    var tel = /^[0-9]{6}$/;      
    return this.optional(element) || (tel.test(value));      

}, "����ȷ��д������������");   