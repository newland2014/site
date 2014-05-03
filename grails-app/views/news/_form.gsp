<%@ page import="fr.newland.users.User; fr.newland.news.News" %>



<div class="fieldcontain ${hasErrors(bean: newsInstance, field: 'author', 'error')} required">
	<label for="author">
		<g:message code="news.author.label" default="Author" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="author" name="author.id" from="${User.list()}" optionKey="id" required="" value="${newsInstance?.author?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: newsInstance, field: 'post', 'error')} ">
	<label for="post">
		<g:message code="news.post.label" default="Post" />
		
	</label>
	<g:textField name="post" value="${newsInstance?.post}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: newsInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="news.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${newsInstance?.title}"/>

</div>

