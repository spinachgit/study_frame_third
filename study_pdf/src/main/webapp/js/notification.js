window.onload = function() {
	// 每10秒弹出一个桌面通知.
	var number = setInterval(notifyMe, 10 * 1000);
}

function notifyMe() {
	var options = {
		dir : "auto",
		lang : "utf-8",
		body : "内容",
		tag : "id", // 标识
		icon : "iconUrl" //
	};
	// 检查浏览器是否支持Notification.
	if (!("Notification" in window)) {
		alert("当前浏览器不支持Notification.");
	} else if (Notification.permission == "granted") {
		// 检查用户是否已授权,安全性第一,顺便还可以避免赖皮广告.
		// 如果已授权,则创建一个Notification对象.
		var notification = new Notification("标题", options);
	} else if (Notification.permission == "denied") {
		// 如果用户拒绝,则用常规的方式提示,比如:alert().
		alert(options.body);
	} else if (Notification.permission == "default") {
		// 用户未授权,则向用户询问是否授权.
		Notification.requestPermission(function(permission) {
			// 用户同意授权,则创建一个Notification对象.
			var notification = new Notification("标题", options);
		});
	}
}

function notify() {
	if (window.Notification) {
		if (Notification.permission === 'granted') {
			// icon_url 图片资源，title:消息标题，content:消息内容
			var notification = new Notification(title, {
				"icon" : icon_url,
				"body" : content
			});
		} else {
			Notification.requestPermission();
		}
		;
	}
}

var notification = new Notification('标题');
notification.onshow = function() {
	setTimeout(function() {
		notification.close();
	}, 3000);
}

function showMsg(title, body, callback) {
	var options = {
			body : body,
			icon : "http://shop.ddt123.cn/favicon.ico"
	};
	
	if (!('Notification' in window)) {
		alert('Sorry bro, your browser is not good enough to display notification');
		return;
	} else if (Notification.permission == "granted") {
		var notification = new Notification(title, options);
		notification.onclick = function() {
			window.open(callback);
		}
	} else {
		Notification.requestPermission(function(permission) {
			if(permission === "granted"){
				var notification = new Notification(title, options);
				notification.onclick = function() {
					window.open(callback);
				}
			}
		});
	}
}
showMsg("温馨提示:", "您有一个新的订单,点击查看", "http://www.baidu.com");