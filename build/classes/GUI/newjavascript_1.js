/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html {width: 100%;height: 100%;margin:0;font-family:"΢���ź�";}
		#allmap{width:100%;height:500px;}
		p{margin-left:5px; font-size:14px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=4GDzKUfLdYLtf0YkniLfvX9EQ3sZSm6T"></script>
	<title>���ݹؼ��ֱ�������</title>
</head>
<body>
	<div id="allmap"></div>
	<p>���ر����С����㡱�ؼ��ֵļ����������չʾ�ڵ�ͼ��</p>
</body>
</html>
<script type="text/javascript">
	// �ٶȵ�ͼAPI����
	var map = new BMap.Map("allmap");          
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map}
	});
	local.search("����ɽ");
</script>

