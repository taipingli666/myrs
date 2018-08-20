<#if page?exists && page.totalPage gt 0>
<div id="pagina" class="paging_area">
    <div class="paging">
    	
    	<!-- 计算显示页码及偏移量 start -->
    	<#assign leftEllipsisDisp = false>
    	<#assign rightEllipsisDisp = false>
    	<#assign startPageIndex = 1>
    	<#if page.totalPage lte 4>
			<#assign startPageIndex = 1>
    		<#assign endPageIndex = page.totalPage>
    	<#else>
    		<#assign startPageIndex = page.currentPage-1>
    		<#assign endPageIndex = page.currentPage+2>
    		
    		<#if startPageIndex lte 0>
    			<#assign startPageIndex = 1>
    		</#if>
    		<#if endPageIndex gt page.totalPage>
    			<#assign endPageIndex = page.totalPage>
    		</#if>
    		
    		<#if page.currentPage == 1>
    			<#assign endPageIndex = 4>
    		</#if>
    		<#if endPageIndex == page.totalPage>
    			<#assign startPageIndex = page.totalPage-3>
    		</#if>
    		
    		<#if startPageIndex gt 1>
    			<#assign leftEllipsisDisp = true>
    		</#if>
    		<#if endPageIndex lt page.totalPage>
    			<#assign rightEllipsisDisp = true>
    		</#if>
    	</#if>
    	<!-- 计算显示页码及偏移量 end -->
    
    	<#if page.isHasPrePage()>
        <a class="prev" href="#" onclick="javascript:${functionName}(${page.currentPage-1});return false;">&lt;&nbsp;上一页</a>
        <#else>
        <span class="prev_null" >上一页</span>
        </#if>
        <#if leftEllipsisDisp>
        <a class="num" href="#" onclick="javascript:${functionName}(1);return false;">1</a>
        <span class="ellipsis">...</span>
        </#if>
        <#list startPageIndex..endPageIndex as index>
        	<#if page.currentPage == index>
        	<span class="num_cur">${index}</span>
        	<#else>
        	<a class="num" href="#" onclick="javascript:${functionName}(${index});return false;">${index}</a>
        	</#if>
		</#list>
		<#if rightEllipsisDisp>
        <span class="ellipsis">...</span>
        <a class="num" href="#" onclick="javascript:${functionName}(${page.totalPage});return false;">${page.totalPage}</a>
        </#if>
        <#if page.isHasNextPage()>
        <a class="next" href="#" onclick="javascript:${functionName}(${page.currentPage+1});return false;">下一页</a>
        <#else>
        <span class="next_null">下一页</span>
        </#if>
    </div>
</div>

</#if>