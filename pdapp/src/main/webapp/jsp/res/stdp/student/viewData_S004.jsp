<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"save":"保存", 
		"del":"删除"
	},
	"inputs":[
		{
			"inputId":"dept",
			"inputType":"select",
			"label":"科室",
			 "options":[{
				"optionId":"肿瘤科",
				"optionDesc":"肿瘤科"
			}],
			"required":true
		},
		{
			"inputId":"time",
			"inputType":"date",
			"label":"时间",
			"required":true
		},
		{
			"inputId":"name",
			"inputType":"text",
			"label":"病名",
			"required":true
		},
		{
			"inputId":"caseNo",
			"inputType":"text",
			"label":"病历号",
			"required":true
		}
	],
	"values":[
		{
			"inputId":"dept",
			"value":"肿瘤科"
		},
		{
			"inputId":"time",
			"value":"2015-07-23"
		},
		{
			"inputId":"name",
			"value":"结肠癌"
		},
		{
			"inputId":"caseNo",
			"value":"150719056"
		}
	]