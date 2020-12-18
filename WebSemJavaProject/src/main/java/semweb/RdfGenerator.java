package semweb;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.vocabulary.XSD;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.lang.System;

public class RdfGenerator {
	
	
	public static void main(String args[]) {
		
		try {
		    // create a reader
			/*Reader TripsReader = Files.newBufferedReader(Paths.get("src/main/resources/trips.txt"));
			Reader StopsReader = Files.newBufferedReader(Paths.get("src/main/resources/stops.txt"));
			Reader TimesReader = Files.newBufferedReader(Paths.get("src/main/resources/stop_times.txt"));*/
			Reader RoutesReader = Files.newBufferedReader(Paths.get("src/main/resources/routes.txt"));

		    // create csv reader
			/*CSVReader csvTripsReader = new CSVReader(TripsReader);
			CSVReader csvStopsReader = new CSVReader(StopsReader);
			CSVReader csvTimesReader = new CSVReader(TimesReader);*/
			CSVReader csvRoutesReader = new CSVReader(RoutesReader);
			
/*
		    // read one Tipe record at a time
		    String[] Tripsrecord;
		    while ((Tripsrecord = csvTripsReader.readNext()) != null) {
		    	
		        String ex = "http://www.example.com/";
				//String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";
				String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
				//String xsd = "http://www.w3.org/2001/XMLSchema#";
				String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
				
				Model model = ModelFactory.createDefaultModel();
				// create properties
				Property a = model.createProperty(rdf + "type");
				Property label = model.createProperty(rdfs + "label");
				
						
				String id = ex + Tripsrecord[0];
				Literal name = model.createLiteral(Tripsrecord[1], "en");
				
				Resource trips = model.createResource(id);
				trips.addProperty(a, ex + "PublicTransportLigne");
				trips.addProperty(label, name);
				
				model.write(System.out, "TURTLE");
				
				String datasetURL = "http://localhost:3030/transportEnCommun-Stetienne";
				String sparqlEndpoint = datasetURL + "/sparql";
				String sparqlUpdate = datasetURL + "/update";
				String graphStore = datasetURL + "/data/Ligne";
				RDFConnection conneg = RDFConnectionFactory.connect(sparqlEndpoint,sparqlUpdate,graphStore);
				conneg.load(model); // add the content of model to the triplestore
				
			}
			
		    // read one record at a time from Stops
		    String[] Stopsrecord;
		    while ((Stopsrecord = csvStopsReader.readNext()) != null) {
		    	
		        String ex = "http://www.example.com/";
				String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";
				String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
				//String xsd = "http://www.w3.org/2001/XMLSchema#";
				String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
				
				Model model = ModelFactory.createDefaultModel();
				// create properties
				Property a = model.createProperty(rdf + "type");
				Property label = model.createProperty(rdfs + "label");
				Property longi=model.createProperty(geo + "longitude");
				Property lati=model.createProperty(geo + "latitude");

				
						
				String id = ex + Stopsrecord[0];
				Literal name = model.createLiteral(Stopsrecord[1], "en");
				String longitude = Stopsrecord[2];
				String latitude = Stopsrecord[3];
				
				
				Resource Stops = model.createResource(id);
				Stops.addProperty(a, ex + "PublicTransportStops");
				Stops.addProperty(label, name);
				Stops.addProperty(longi,longitude, XSDDatatype.XSDdecimal);
				Stops.addProperty(lati,latitude, XSDDatatype.XSDdecimal);
				
				model.write(System.out, "TURTLE");
				
				String datasetURL = "http://localhost:3030/transportEnCommun-Stetienne";
				String sparqlEndpoint = datasetURL + "/sparql";
				String sparqlUpdate = datasetURL + "/update";
				String graphStore = datasetURL + "/data/Stops";
				RDFConnection conneg = RDFConnectionFactory.connect(sparqlEndpoint,sparqlUpdate,graphStore);
				conneg.load(model); // add the content of model to the triplestore
				
			}
			
		    // read one record at a time from the Stop_Times
		    String[] Timesrecord;
		    while ((Timesrecord = csvTimesReader.readNext()) != null) {
		    	
		        String ex = "http://www.example.com/";
				//String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";
				String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
				//String xsd = "http://www.w3.org/2001/XMLSchema#";
				String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
				
				Model model = ModelFactory.createDefaultModel();
				// create properties
				Property a = model.createProperty(rdf + "type");
				Property label = model.createProperty(rdfs + "label");
				Property Arrivaltime = model.createProperty(rdfs + "ArrTime");
				Property Departuretime = model.createProperty(rdfs + "Deptime");

				
						
				String id = ex + Timesrecord[0];
				Literal name = model.createLiteral(Timesrecord[1], "en");
				String Arr=Timesrecord[1];
				String Dep=Timesrecord[2];
				
				Resource Times = model.createResource(id);
				Times.addProperty(a, ex + "Time");
				Times.addProperty(label, name);
				Times.addProperty(Arrivaltime,Arr, XSDDatatype.XSDtime);
				Times.addProperty(Departuretime,Dep, XSDDatatype.XSDtime);


				
				model.write(System.out, "TURTLE");
				
				String datasetURL = "http://localhost:3030/transportEnCommun-Stetienne";
				String sparqlEndpoint = datasetURL + "/sparql";
				String sparqlUpdate = datasetURL + "/update";
				String graphStore = datasetURL + "/data/StopTimes";
				RDFConnection conneg = RDFConnectionFactory.connect(sparqlEndpoint,sparqlUpdate,graphStore);
				conneg.load(model); // add the content of model to the triplestore
				
			}
			*/
		    // read one record at a time
		    String[] Routesrecord;
		    while ((Routesrecord = csvRoutesReader.readNext()) != null) {
		    	
		        String ex = "http://www.example.com/";
				//String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";
				//String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
				//String xsd = "http://www.w3.org/2001/XMLSchema#";
				String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
				
				Model model = ModelFactory.createDefaultModel();
				// create properties
				Property a = model.createProperty(rdf + "type");
				Property LigneshortName=model.createProperty(rdf + "shortName");
				Property LigneLongName=model.createProperty(rdf +"LongName");
				
						
				String id = ex + Routesrecord[0];
				String route_short_name=Routesrecord[2];
				String route_long_name=Routesrecord[3];
				
				Resource Routes = model.createResource(id);
				Routes.addProperty(a, ex + "PublicTransportLigne");
				Routes.addProperty(LigneshortName, route_short_name,XSDDatatype.XSD);
				Routes.addProperty(LigneLongName, route_long_name,XSDDatatype.XSD);
				
				model.write(System.out, "TURTLE");
				
				String datasetURL = "http://localhost:3030/transportEnCommun-Stetienne";
				String sparqlEndpoint = datasetURL + "/sparql";
				String sparqlUpdate = datasetURL + "/update";
				String graphStore = datasetURL + "/data";
				RDFConnection conneg = RDFConnectionFactory.connect(sparqlEndpoint,sparqlUpdate,graphStore);
				conneg.load(model); // add the content of model to the triplestore
				
		    }

		    // close readers
		   /* csvTripsReader.close();
			TripsReader.close();
			csvStopsReader.close();
			StopsReader.close();
			csvTimesReader.close();
			TimesReader.close();*/
			csvRoutesReader.close();
			RoutesReader.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
	
}
