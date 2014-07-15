new Ext.onReady(function(){
	
	var svmList = new Ext.tree.TreePanel({
		region: 'west',
		useArrows: true,
	    autoScroll: true,
	    animate: true,
	    enableDD: true,
	    containerScroll: true,
	    border: false,
        collapsible: true,
        width: 200,
        xtype: 'treepanel',
        margins : '5 5 0 5',
        dataUrl : 'svmlistaction.action',
        
        root: {
            nodeType: 'async',
            text: '训练器列表',
            draggable: false,
            id: 'source'
        },
        listeners :{
        	click:function(n){
        		if(!n.isLeaf()){
        			Ext.Msg.alert('ok');
        		}
        	}
        }
	});
	svmList.getRootNode().expand();
	
	var imageList = new Ext.Panel({
		region: 'east',
        title: '图片列表',
        collapsible: true,
        split: true,
        width: 200,
        margins : '5 5 0 5'
	});
	
	var pictureInfor = new Ext.Panel({
		region: 'center',
		layout: 'border',
		title : '图片信息',
        margins : '5 0 0 5'
	});
	
	var toolPanel = new Ext.Panel({
		region: 'south',
		title : '操作工具',
		height : 200
	});
	
	new Ext.Viewport({
	    layout: 'border',
	    items: [
	            svmList, 
	            imageList, 
	            pictureInfor
	   ]
	});
});
