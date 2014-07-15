new Ext.onReady(function() {
	Ext.BLANK_IMAGE_URL = 'ext-3.4.1/resources/images/default/s.gif';
	
	var imageInforPanel = new Ext.Panel({
		region : 'center',
		title : '图片信息',
		margins : '5 5 5 5',
		html : "<img id = 'imageLoad' src='http://192.168.0.171/cgi-bin/random_image.pl' style='width:600px;height:500px'></img>"
	});
	
	var fpFileUpload = new Ext.FormPanel({
		id : 'fpFileUpload',
		frame : true,
		fileUpload : true,
		items : [ {
			xtype : 'textfield',
			allowBlank : false,
			fieldLabel : '选择文件',
			inputType : 'file',
			name : 'files'
		} ],
		buttonAlign : 'center',
		buttons : [ {
			text : '上传',
			handler : function() {
				if (fpFileUpload.form.isValid()) {
					fpFileUpload.form.submit({
						method : 'post',
						url : 'upload/uploadFile.action',
						dataType : 'json',
						waitMsg : '文件上传中...',
						success : function() {
							Ext.Msg.alert("系统提示", "文件上传成功！");
						},
						failure : function() {
							Ext.Msg.alert("系统提示", "文件上传成功！");
						}
					});
				} else {
					Ext.Msg.alert("系统提示", "请选择文件后再上传！");
				}
			}
		}, {
			text : '取消',
			handler : function() {
				winFielUpload.hide();
			}
		} ]
	});

	var winFielUpload = new Ext.Window({
		id : 'win',
		title : '文件上传',
		width : 350,
		height : 100,
		layout : 'fit',
		autoDestory : true,
		modal : true,
		closeAction : 'hide',
		items : [ fpFileUpload ]
	});
	window.winFielUpload = winFielUpload;

	function showWindow() {
		winFielUpload.show();
	}

	
	//进度条显示窗口
	function prowin(){
		
		var progBar = new Ext.ProgressBar({
			id : 'progBar',
			msg : '',
			animate : true,
			progress : true,
//			x : 700,
//			y : 30,
			height : 50,
			width : 500
		});
		
		var progressWin = new Ext.Window({
			id : 'progressWin',
			title : '检测进度',
			width : 500,
			height : 100,
//			layout : 'fit',
			layout : 'absolute',
			autoDestory : true,
			modal : true,
			closeAction : 'close',
			items : [ progBar],
			html : "<span id='infor'>fuck</span>",
			
			buttonAlign : 'center',
			buttons : [
			     {
			    	 text : '确定',
			    	 id : 'proCloBtn',
			    	 disabled : true,
					handler : function() {
						progressWin.close();
					}
			     }
			]
		});
		
		
		
		
		function startTask(){
			// 滚动条被刷新的次数
			var index = 0;
			var count = 1;
			// 进度百分比
			var percentage = 0;
			// 进度条信息
			var processText = '';
			
			function getCheckProgressInfor(){
				Ext.Ajax.request({
					url : 'checkinfor.action',
					success : function(response, options){
						var responseJson = Ext.util.JSON.decode(response.responseText);
						index = responseJson.index;
						count = responseJson.count;
					},
					failure : function(response, options){
						var responseJson = Ext.util.JSON.decode(response.responseText);
						index = responseJson.index;
						count = responseJson.count;
					},
					params: { 'md5': 'md5' }
				});
			}
			
			var task = {
					run : function(){
						getCheckProgressInfor();
						percentage = index / count;
						processText = '当前进度：' + parseInt((index / count) * 100) + '%';
						progBar.updateProgress(percentage, processText);
						
						if(index >= count){
							processText = '检测完成';
							progBar.updateProgress(percentage, processText);
							Ext.getCmp('proCloBtn').enable();
							Ext.TaskMgr.stop(this);
						}
					}, interval : 1000
			}
			Ext.TaskMgr.start(task);
		}
		
		progressWin.show();
		startTask();
			
	}
	
	
	var toolPanel = new Ext.Panel({
		layout : 'absolute',
		region : 'north',
		title : '操作',
		collapsible : true,
		split : true,
		height : 150,
		margins : '5 5 5 5',
		minHeight : 100,
		items : [ 
		 new Ext.Button({
			id : 'uploadBtn',
			width : 50,
			height : 50,
//			scale: 'large',
//            iconAlign: 'top',
            text : '上传文件',
//			cls : 'uploadBtnCls',
			x : 100,
			y : 50,
			listeners : {
				click : function(n) {
					showWindow();
				}
			}
		}), new Ext.form.Label({
			text : '检测：',
			x : 450,
			y : 50,
		}), new Ext.Button({
			id : 'posiBtn',
			text : '正样本',
			width : 50,
			height : 50,
			x : 500,
			y : 50,
			listeners : {
				click : function(n) {
					prowin();
				}
			}
		}), new Ext.Button({
			id : 'negBtn',
			text : '负样本',
			width : 50,
			height : 50,
			x : 700,
			y : 50,
			listeners : {
				click : function(n) {
					Ext.Msg.alert('----');
				}
			}
		}), new Ext.Button({
			id : 'stopBtn',
			text : '终止',
			width : 50,
			height : 50,
			x : 900,
			y : 50,
			listeners : {
				click : function(n) {
					Ext.Msg.alert('----');
				}
			}
		}), new Ext.Button({
			id : 'outPutBtn',
			text : '导出',
			width : 50,
			height : 50,
			x : 1500,
			y : 50,
			listeners : {
				click : function(n) {
					Ext.Msg.alert('-ff---');
				}
			}
		}) ]
	});
	
	var cm = new Ext.grid.ColumnModel({
		columns:
		[{
		header : "编号",
		dataIndex : "id",
		width : 130,
		align : "center",
		sortable : true
		},
		{
		header : "名称",
		dataIndex : "name",
		width : 220
		}, {
		header : "置信度",
		dataIndex : "credit",
		width : 150,
		sortable : true
		}]
	});
	cm.defaultSortable = true;

	var httpProxy = new Ext.data.HttpProxy({
		method : "post",
		url : ""
	});

	var store = new Ext.data.Store({
		reader : new Ext.data.JsonReader({
			idProperty : 'id',
			root : 'rows',
			totalProperty : 'results',
			fields : [ {
				name : 'id'
			}, {
				name : 'name'
			}, {
				name : 'credit'
			} ]
		}),
		// url : "imagelist.action"
		proxy : httpProxy
	});

	var bbar = new Ext.PagingToolbar({
		pageSize : 10,
		store : store,
		displayInfo : true,
		displayMsg : '显示第{0}条到{1}条,一共{2}条',
		emptyMsg : '没有记录'
	});

	var imageListPanel = new Ext.grid.GridPanel({
		region : 'east',
		title : '图片列表',
		height : 600,
		collapsible : true,
		split : true,
		width : 500,
		margins : '5 5 0 5',
		bodyStyle : 'width:100%',
		loadMask : {
			msg : '正在加载数据，请稍后.....'
		},
		ds : store,
		cm : cm,
		bbar : bbar,
	});
	
	

	var svmList = new Ext.tree.TreePanel({
		region : 'west',
		title : '训练器列表',
		useArrows : true,
		autoScroll : true,
		animate : true,
		enableDD : true,
		containerScroll : true,
		border : true,
		collapsible : true,
		width : 200,
		xtype : 'treepanel',
		margins : '5 5 5 5',
		dataUrl : 'svmlistaction.action',
		root : {
			nodeType : 'async',
			text : '训练器列表',
			draggable : false,
			id : 'source'
		},
		listeners : {
			click : function(n) {
				if (n.isLeaf()) {
					// 此处添加向后台传递的参数
					httpProxy.setUrl('imagelist.action', true);
					store.load();
				}
			}
		}
	});
	svmList.getRootNode().expand();

	new Ext.Viewport({
		layout : 'border',
		items : [ svmList, toolPanel, imageListPanel, imageInforPanel ]

	});
	
	
	imageListPanel.addListener('cellclick', cellclick);
	function cellclick(){
		var record = imageListPanel.getSelectionModel().getSelected();
		var imageName = record.get('name');
		//var imageLoad = Ext.getCmp('imageLoad');
		document.getElementById('imageLoad').setAttribute('src','http://192.168.0.171/cgi-bin/random_image.pl?imageName='+imageName   );
	};
	
	new Ext.Resizable('imageLoad', {
	    wrap:true,
	    pinned:true,
	    minWidth:50,
	    minHeight: 50,
	    maxWidth: 2000,
	    maxHeight: 700,
	    preserveRatio: true
	});
	
	
});
