<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$('#table').bootstrapTable({
    columns: [{
    	checkbox: 'true'
    },{
    	title: "编号",
    	rowStyle: function(row,index){
    		return 2;
    	}
    },{
        field: 'id',
        title: 'Item ID'
    }, {
        field: 'name',
        title: 'Item Name'
    }, {
        field: 'price',
        title: 'Item Price'
    }],
    data: [{
        id: 1,
        name: 'Item 1',
        price: '$1'
    }, {
        id: 2,
        name: 'Item 2',
        price: '$2'
    }, {
        id: 2,
        name: 'Item 2',
        price: '$2'
    }, {
        id: 2,
        name: 'Item 2',
        price: '$2'
    }, {
        id: 2,
        name: 'Item 2',
        price: '$2'
    }, {
        id: 2,
        name: 'Item 2',
        price: '$2'
    }, {
        id: 2,
        name: 'Item 2',
        price: '$2'
    }, {
        id: 2,
        name: 'Item 2',
        price: '$2'
    }, {
        id: 2,
        name: 'Item 2',
        price: '$2'
    }],
    pagination: true,
    //pageNumber: 1,
    //pageSize: 3,
    //showPaginationSwitch:true,
	pageList: [3, 6, 9, 100]
	
});
</script>
<table id="table" 
	data-toggle="table" 
	data-height="300" ></table>