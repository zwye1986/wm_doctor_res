<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
		{
            "inputId": "paperDate",
            "label": "发表日期",
            "required":true,
            "inputType": "date"
        },
    	{
            "inputId": "paperTitle",
            "label": "论文题目",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "paperTypeId",
            "label": "论文类别",
            "required":true,
            "inputType": "select",
            "options": [
				{
                    "optionId": "1",
                    "optionDesc": "专业期刊发表的文章"
                },
				{
                    "optionId": "2",
                    "optionDesc":"学术会议交流论文"
                },
				{
                    "optionId": "3",
                    "optionDesc":"院（所）级学时会议交流文章"
                },
				{
                    "optionId": "4",
                    "optionDesc":"提交文章"
                }
            ]
        },
        {
            "inputId": "publishedJournals",
            "label": "发表刊物",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "author",
            "label": "第几作者",
            "required":true,
            "inputType": "text"
        }
