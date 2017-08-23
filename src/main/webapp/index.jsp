<%@page import="nl.sogyo.mancala.*" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mancala</title>
    <link type='text/css' href='${pageContext.request.contextPath}/css/style.css' rel='stylesheet'>
</head>
<body>
<div class="mainwrapper">
    <div class="backgroundwrapper">
        <div class="content">
            <h1>Mancala</h1>
            <% Game game = (Game) session.getAttribute("game"); %>
            <h2><%= game.getPlayer().getName() %>
                versus
                <%= game.getPlayer().getNemesis().getName() %>
            </h2>
            <% ArrayList<Field> fields = game.getFields(); %>

            <ul style="padding-right:100px">
                <li class="kalaha"><%= fields.get(13).getStones() %>
                </li>
                <li><a href="?picked=12"><%= fields.get(12).getStones() %></a>
                </li>
                <li><a href="?picked=11"><%= fields.get(11).getStones() %></a>
                </li>
                <li><a href="?picked=10"><%= fields.get(10).getStones() %></a>
                </li>
                <li><a href="?picked=9"><%= fields.get(9).getStones() %></a>
                </li>
                <li><a href="?picked=8"><%= fields.get(8).getStones() %></a>
                </li>
                <li><a href="?picked=7"><%= fields.get(7).getStones() %></a>
                </li>
            </ul>
            <ul style="padding-left:100px">
                <li><a href="?picked=0"><%= fields.get(0).getStones() %></a>
                </li>
                <li><a href="?picked=1"><%= fields.get(1).getStones() %></a>
                </li>
                <li><a href="?picked=2"><%= fields.get(2).getStones() %></a>
                </li>
                <li><a href="?picked=3"><%= fields.get(3).getStones() %></a>
                </li>
                <li><a href="?picked=4"><%= fields.get(4).getStones() %></a>
                </li>
                <li><a href="?picked=5"><%= fields.get(5).getStones() %></a>
                </li>
                <li class="kalaha"><%= fields.get(6).getStones() %>
                </li>
            </ul>
            <a href="?restart=true"><div id="restart">Restart Game</div></a>
        </div>
    </div>
</div>
</body>
</html>