<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
@Test
public void givenEntitiesExist_whenRetrievingLastPage_thenCorrectSize() {
    int pageSize = 10;
    String countQ = "Select count (f.id) from Foo f";
    Query countQuery = session.createQuery(countQ);
    Long countResults = (Long) countQuery.uniqueResult();
    int lastPageNumber = (int) ((countResults / pageSize) + 1);
 
    Query selectQuery = session.createQuery("From Foo");
    selectQuery.setFirstResult((lastPageNumber - 1) * pageSize);
    selectQuery.setMaxResults(pageSize);
    List<Foo> lastPage = selectQuery.list();
 
    assertThat(lastPage, hasSize(lessThan(pageSize + 1)));
}
</body>
</html>