<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
    	{
            "inputId": "activity_date",
            "label": "日期",
            "required":true,
            "inputType": "date"
        },
    	{
            "inputId": "activity_content",
            "label": "内容",
            "required":true,
            "inputType": "textarea"
        },
        {
            "inputId": "activity_way",
            "label": "活动形式",
            "required":true,
            "inputType": "select",
            "options": [
                <c:forEach items="${activityWayList}" var="activityWay" varStatus="status">
			        {
	                    "optionId": ${pdfn:toJsonString(activityWay.dicitem)},
	                    "optionDesc": ${pdfn:toJsonString(activityWay.dicitem)}
	                }
			        <c:if test="${not status.last }">
							,
					</c:if>
				</c:forEach>
            ]
        },
        {
            "inputId": "activity_period",
            "label": "学时",
            "required":true,
            "inputType": "select",
            "options": [
                {
                    "optionId": "1",
                    "optionDesc": "1"
                },
                {
                    "optionId": "2",
                    "optionDesc": "2"
                },
                {
                    "optionId": "3",
                    "optionDesc": "3"
                },
                {
                    "optionId": "4",
                    "optionDesc": "4"
                },
                {
                    "optionId": "5",
                    "optionDesc": "5"
                }
            ]
        },
        {
            "inputId": "activity_speaker",
            "label": "主讲人",
            "required":true,
            "inputType": "text"
        }