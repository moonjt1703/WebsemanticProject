<?php 

require_once( "sparqllib.php" );
 
$db = sparql_connect( "http://sparql.data.southampton.ac.uk/" );
if( !$db ) { print sparql_errno() . ": " . sparql_error(). "\n"; exit; }
sparql_ns( "PublicTransportLine","http://www.example.com/PublicTransportLigne" );
 
$sparql = "SELECT DISTINCT * WHERE { ?x a rdf:PublicTransportLigne .} ";
$result = sparql_query( $sparql ); 
if( !$result ) { print sparql_errno() . ": " . sparql_error(). "\n"; exit; }
 
$fields = sparql_field_array( $result );
 
print "<p>Number of rows: ".sparql_num_rows( $result )." results.</p>";
print "<table class='example_table'>";
print "<tr>";
foreach( $fields as $field )
{
	print "<th>$field</th>";
}
print "</tr>";
while( $row = sparql_fetch_array( $result ) )
{
	print "<tr>";
	foreach( $fields as $field )
	{
		print "<td>$row[$field]</td>";
	}
	print "</tr>";
}
print "</table>";
 
 


 ?>