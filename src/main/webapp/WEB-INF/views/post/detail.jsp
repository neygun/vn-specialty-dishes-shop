<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<h1>${post.title}</h1>
<img src="/static/images/posts/${post.image}" class="post-img">
<div class="text-justify">
	<p style="white-space: pre-line">${post.content}</p>
</div>