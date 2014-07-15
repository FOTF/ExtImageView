
Ext.onReady(function(){
    Ext.QuickTips.init();

    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

    // sample static data for the store
    var myData = [
        ['3m Co',                               71.72, 0.02],
        ['Alcoa Inc',                           29.01, 0.42],
        ['Altria Group Inc',                    83.81, 0.28]
    ];

    // create the data store
    var store = new Ext.data.ArrayStore({
    	reader:new Ext.data.JsonReader({  
            root:"root",
            totalProperty:"totalProperty",//从数据库中读取的总记录数  
            fields:[{name:'id'},{name:'name'},{name:'credit'}]
        }),  
        proxy:new Ext.data.HttpProxy({  
            method:"post",  
            url:"imagelist.action"       
        })
        /*fields: [
           {name: 'company'},
           {name: 'price',      type: 'float'},
           {name: 'change',     type: 'float'}
        ]*/
    });

    // manually load local data
    store.load(); 
    //store.loadData(myData);

    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {
                id       :'company',
                header   : 'Company', 
                width    : 160, 
                sortable : true, 
                dataIndex: 'company'
            },
            {
                header   : 'Price', 
                width    : 75, 
                sortable : true, 
                dataIndex: 'price'
            },
            {
                header   : 'Change', 
                width    : 75, 
                sortable : true, 
                dataIndex: 'change'
            }
            ],
        stripeRows: true,
        autoExpandColumn: 'company',
        height: 350,
        width: 600,
        title: 'Array Grid',
        stateful: true,
        stateId: 'grid'
    });

    // render the grid to the specified div in the page
    grid.render('grid-example');
});