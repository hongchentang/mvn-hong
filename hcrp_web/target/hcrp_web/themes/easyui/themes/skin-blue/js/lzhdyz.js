+function (factory) {
	"use strict";
	if(typeof define ==='function'){ 
		define(['jquery'],factory);
	}else if(typeof module !=='undefined' && module!==null){
		module.exports = factory(jQuery);
	}else{
		factory(jQuery);
	}

}(function($){
	"use strict";
    var lzhdyzValidat = {

        name: '滑动验证',

		version:'1.0.4',

		author:'lzx',

		date:'2018-11-14'
	}

	$.extend({

		isObject: function(v) {
			return Object.prototype.toString.call(v) === '[object Object]';
		},

		isFunction:function(v){
			return Object.prototype.toString.call(v) ==='[object Function]';
		},

		isString:function(v){
			return Object.prototype.toString.call(v) ==='[object String]';
		}

	});


	var hdyzEvent = function(val){
		var me = this;
		me.$ele = $(val);
		me.modalMethods = me.$ele.data('hdyzMethods');
		me.hdyzInit = me.$ele.data('hdyzInit');
		me.param = me.$ele.data('lzhdyz-param');
		me.status = me.$ele.data('lzhdyzt-status');
		me.$ele.data('hdyzEvent',me);
	}

	hdyzEvent.prototype = {

		constructor:hdyzEvent,

		setEvent:function(){
			var event = this;
			$.each(event.param.event,function(index,val){
				event[val] && event[val]();
			})
		},

		drag:function(){
			var me = this;
			var that = me.modalMethods;
			var $slidingBox = me.$ele.find('.w-sliding-box');
			var $bar = me.$ele.find('.w-sliding-bar');

			$bar.on('touchstart mousedown', function (e) {
				e.preventDefault();
	            if(!that.pass){
	            	that.flag = true;
	            	me.$ele.find('.lz-sliding-validation').removeClass('is-error');
	            	!that.parentWid && (that.parentWid = me.$ele.find('.lz-sliding-validation').width());
	            }
	            return false;
	        });

	        $(window).on('touchmove mousemove', function (e) {
	        	e.preventDefault();
	            if (that.flag) {
	            	var move = '';
	                var touch = e.originalEvent.touches ? e.originalEvent.touches[0] : null;
	                if(touch){
	                	move = touch.pageX - $slidingBox.offset().left;
	                }else{
	                	move = e.pageX - $slidingBox.offset().left;
	                }
	               	that._move(move)
	            }
	            return false;
	        });

	        $(window).on('touchend mouseup', function (e) {
	        	if(that.wid.inw<98){
	        		me.$ele.find('.lz-sliding-validation').removeClass('is-ok');
	        		that._setCss(0);
	        		clearTimeout(that.tim);
	        		that.tim= '';
	        	}
	        	$(this).remove();
	            that.flag = false;
	        });
		}

	}

	// 方法�?
	var hdyzMethods = function(val){
		var me = this;
		me.$ele = $(val);
		me.param = me.$ele.data('lzhdyz-param');
		me.status = me.$ele.data('lzhdyz-status');
		me.hdyzInit = me.$ele.data('hdyzInit');
		me.$ele.data('hdyzMethods',me);

		me.flag = false;
		me.pass = false;
		me.parentWid = '';
		me.wid = {};
		me.tim ='';
	}

	hdyzMethods.prototype = {

		constructor:hdyzMethods,

		_move:function(move){
			var me = this;
			if (move < 0) {
                move = 0
            } else if (move > me.parentWid) {
                move = me.parentWid
            }
            me.tim = setTimeout(function(){
            	if(me.flag){
            		me.wid.inw = move / me.parentWid * 100;
                	me._setCss(me.wid.inw);
            	}
            	if(me.wid.inw>98){
            		me.$ele.find('.lz-sliding-validation').addClass('is-ok');
	        		me._setCss(100);
	        		me.$ele.find('.w-sliding-inner-text').text(me.param.success);
	        		me.pass = true;
	        		(me.pass && me.flag) && me._callback.call(me._tool)
	        		me.flag = false;
	        	}
	        	clearTimeout(me.tim);
	        	me.tim = '';
            },50)   
		},

		_callback:function(){

		},

		_setCss:function(val){;
			this.$ele.find('.w-sliding-box').css('width',val + '%')
		},

		_isError:function(){
			var me = this;
			!me.pass && me.$ele.find('.lz-sliding-validation').addClass('is-error');
		},

		_isRefresh:function(){
			var me = this;
			me._setCss(0);
			me.$ele.find('.lz-sliding-validation').removeClass('is-ok is-error');
			me.$ele.find('.w-sliding-inner-text').text(me.param.text);
			me.pass = false;
		}
	}

	// 初始�?

	var hdyzInit = function(val,a){
		var me = this;
		me.$ele = $(val);
		me.$ele.data('lzhdyz-param',me._initParam(a));
		me.$ele.data('lzhdyz-status',{});
		me.$ele.data('hdyzInit',me);
	}

	hdyzInit.prototype = {

		constructor:hdyzInit,

		defaults:{
			
			'event':['drag'],
		},

		// 初始化参�?
		_initParam:function(a){

			var param = this.defaults;

			if($.isObject(a)){
				param = $.extend(true,{},param,a);
			}
			return param;
		},

		// 初始化版�?
		_layout:function(){
			var me = this;
			var param = me.$ele.data('lzhdyz-param');
			var status = me.$ele.data('lzhdyz-status');

			var html = '<div class="w-sliding-right lz-sliding-validation">'+
				'<div class="w-sliding-inner">'+
					'<div class="w-sliding-box">'+
						'<div class="w-sliding-bar"></div>'+
					'</div>'+
					'<div class="w-sliding-text-mask"></div>'+
					'<div class="w-sliding-inner-text">'+param.text+'</div>'+
				'</div>'+
			'</div>';
			me.$ele.html(html);
		},

		// 初始化事�?
		_initEvent:function(){
			var me = this.$ele,
			param = me.data('lzhdyz-param'),
			status = me.data('lzhdyz-status'),
			event = new hdyzEvent(me);
			event.setEvent();
		},

		_init:function(){
			var self = this.$ele.get(0);
			this._layout();
			self.hdyzMethods = new hdyzMethods(self);
			this._initEvent();
		}

	}

	var tool = function(val){
		var me = this;
		me.$ele = $(val);
		me.param = me.$ele.data('lzhdyz-param');
		me.hdyzMethods = me.$ele.data('hdyzMethods');
		me.$ele.data('tool',me);
	}

	tool.prototype = {

		constructor: tool,

		// 提交数据
		onPass:function(fn){
			var me = this;
			!me.hdyzMethods._tool && (me.hdyzMethods._tool = me);
			$.isFunction(fn) && (me.hdyzMethods._callback = fn);
		},

		error:function(){
			this.hdyzMethods._isError()
		},

		// 刷新插件
		refresh:function(){
			this.hdyzMethods._isRefresh();
		},

		// 重新调用插件
		// init:function(a){

		// },


	}	

	// 插件入口
	$.fn.lzhdyz = function(option,status){
		return $.each(this,function(index,val){
			var newModal = $(val).data('lzhdyz');
			if(!newModal){
				$.isObject(option) && (status = option);
				newModal = new hdyzInit(val,option,status)
				$(val).data('lzhdyz',newModal);
			}
		
			if($.isObject(option)){
				newModal._init(option);
			}else if($.isString(option)){
				var initTool  = $(val).data('hdyzTool');
				if(!initTool){
					initTool = new tool(val);
					$(val).data('hdyzTool',initTool);
				} 
				!!initTool[option] && initTool[option](status);
			}
		});
	}
});