<!--_menu 作为公共模版分离出去-->
<aside class="Hui-aside">	
	<div class="menu_dropdown bk_2">
		<#list smenu as menu> 
		<#if menu.parentId == 0>
		<dl id="menu-article">
			<dt <#if showPId?exists && menu.menuId == showPId?number>class="selected"</#if>><i class="Hui-iconfont">&#xe616;</i> ${menu.menuName}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd <#if showPId?exists && menu.menuId == showPId?number>style="display:block"</#if>>
				<ul>
					<#list smenu as cmenu> 
						<#if cmenu.parentId == menu.menuId>
							<li <#if showId?exists && cmenu.menuId == showId?number>class="current"</#if>><a href="${base}${cmenu?if_exists.url?if_exists}?showId=${cmenu?if_exists.menuId?if_exists}&showPId=${cmenu?if_exists.parentId?if_exists}" title="${cmenu.menuName}">${cmenu.menuName}</a></li>
						</#if>
					</#list>
				</ul>
			</dd>
		</dl>
		</#if>
		</#list>
		
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>