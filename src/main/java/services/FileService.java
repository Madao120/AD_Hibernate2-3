package services;

import model.Adestrador;
import model.Pokedex;
import model.Pokemon;

import javax.xml.stream.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FileService {
    public static void serializar(String ruta, Pokemon pokemon) {

        File file = new File(ruta);

        try (FileOutputStream FoS = new FileOutputStream(file);
             ObjectOutputStream OoS = new ObjectOutputStream(FoS)) {
            OoS.writeObject(pokemon);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Pokemon desSerializar(String ruta) {

        File file = new File(ruta);
        Pokemon pokeSerial = null;

        try (FileInputStream FiS = new FileInputStream(file);
             ObjectInputStream OiS = new ObjectInputStream(FiS)) {
            pokeSerial = (Pokemon) OiS.readObject();
            return pokeSerial;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Serializar por listas
    public static void serializarPokedex(String ruta, ArrayList<Pokedex> lista) {

        File file = new File(ruta);

        try (FileOutputStream FoS = new FileOutputStream(file);
             ObjectOutputStream OoS = new ObjectOutputStream(FoS)) {
            OoS.writeObject(lista);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Desserializar por listas
    public static ArrayList<Pokedex> desSerializarPokedex(String ruta) {
        File file = new File(ruta);
        ArrayList<Pokedex> listaSerial = null;

        try (FileInputStream FiS = new FileInputStream(file);
             ObjectInputStream OiS = new ObjectInputStream(FiS)) {
            listaSerial = (ArrayList<Pokedex>) OiS.readObject();
            return listaSerial;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Serializar por listas Adestradores
    public static void serializarAdestrador(String ruta, ArrayList<Adestrador> lista) {

        File file = new File(ruta);

        try (FileOutputStream FoS = new FileOutputStream(file);
             ObjectOutputStream OoS = new ObjectOutputStream(FoS)) {
            OoS.writeObject(lista);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Desserializar por listas Adestradores
    public static ArrayList<Adestrador> desSerializarAdestrador(String ruta) {
        File file = new File(ruta);
        ArrayList<Adestrador> listaSerial = null;

        try (FileInputStream FiS = new FileInputStream(file);
             ObjectInputStream OiS = new ObjectInputStream(FiS)) {
            listaSerial = (ArrayList<Adestrador>) OiS.readObject();
            return listaSerial;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void transformarXML(ArrayList<Pokemon> lista, String ruta){
        XMLOutputFactory XOF = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter XSW = XOF.createXMLStreamWriter(new FileWriter(ruta));
            XSW.writeStartDocument("1.0");
            XSW.writeCharacters("\n");

            XSW.writeStartElement("Pokemons");
            XSW.writeCharacters("\n");

            for (Pokemon pokemon : lista){
                XSW.writeStartElement("pokemon");
                XSW.writeAttribute("tipo", String.valueOf(pokemon.getNacemento()));
                XSW.writeCharacters("\n");

                XSW.writeCharacters("El pokemon se llama " + pokemon.getNome());
                XSW.writeCharacters("\n");

                XSW.writeEndElement();
                XSW.writeCharacters("\n");
            }

            XSW.flush();
            XSW.writeEndDocument();
            XSW.close();

        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void transformarXMLPokedex(
            ArrayList<Pokedex> lista,
            String ruta) {

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try (FileWriter fileWriter = new FileWriter(ruta)) {
            XMLStreamWriter writer =
                    factory.createXMLStreamWriter(fileWriter);

            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeCharacters("\n");

            writer.writeStartElement("pokedex");
            writer.writeCharacters("\n");

            for (Pokedex pokemon : lista) {
                writer.writeStartElement("pokemon");
                writer.writeAttribute(
                        "id",
                        String.valueOf(pokemon.getId())
                );
                writer.writeCharacters("\n");

                writer.writeStartElement("nome");
                writer.writeCharacters(pokemon.getNome());
                writer.writeEndElement();
                writer.writeCharacters("\n");

                writer.writeStartElement("peso");
                writer.writeCharacters(
                        String.valueOf(pokemon.getPeso())
                );
                writer.writeEndElement();
                writer.writeCharacters("\n");

                writer.writeStartElement("misc");
                writer.writeCharacters(pokemon.getMisc());
                writer.writeEndElement();
                writer.writeCharacters("\n");

                writer.writeEndElement();
                writer.writeCharacters("\n");
            }

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.close();

        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(
                    "Erro ao crear o XML da Pokedex",
                    e
            );
        }
    }

    public static void transformarXMLAdestradores(
            ArrayList<Adestrador> lista,
            String ruta) {

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try (FileWriter fileWriter = new FileWriter(ruta)) {
            XMLStreamWriter writer =
                    factory.createXMLStreamWriter(fileWriter);

            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeCharacters("\n");

            writer.writeStartElement("adestradores");
            writer.writeCharacters("\n");

            for (Adestrador adestrador : lista) {
                writer.writeStartElement("adestrador");
                writer.writeAttribute(
                        "id",
                        String.valueOf(adestrador.getId())
                );
                writer.writeCharacters("\n");

                writer.writeStartElement("nome");
                writer.writeCharacters(adestrador.getNome());
                writer.writeEndElement();
                writer.writeCharacters("\n");

                writer.writeStartElement("nacemento");
                writer.writeCharacters(
                        String.valueOf(adestrador.getNacemento())
                );
                writer.writeEndElement();
                writer.writeCharacters("\n");

                writer.writeEndElement();
                writer.writeCharacters("\n");
            }

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.close();

        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(
                    "Erro ao crear o XML dos adestradores",
                    e
            );
        }
    }

    public static ArrayList<Adestrador> desSerializarXMLAdestradores(String rutaXML) {
        ArrayList<Adestrador> listaAdestradores = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();

        try (FileReader fileReader = new FileReader(rutaXML)) {
            XMLStreamReader reader = factory.createXMLStreamReader(fileReader);

            try {
                while (reader.hasNext()) {
                    int evento = reader.next();

                    if (evento == XMLStreamReader.START_ELEMENT
                            && "adestrador".equals(reader.getLocalName())) {

                        Long id = Long.valueOf(
                                reader.getAttributeValue(null, "id")
                        );

                        String nome = null;
                        LocalDate nacemento = null;

                        while (reader.hasNext()) {
                            evento = reader.next();

                            if (evento == XMLStreamReader.START_ELEMENT) {
                                switch (reader.getLocalName()) {
                                    case "nome":
                                        nome = reader.getElementText();
                                        break;

                                    case "nacemento":
                                        nacemento = LocalDate.parse(
                                                reader.getElementText()
                                        );
                                        break;

                                    default:
                                        break;
                                }
                            } else if (evento == XMLStreamReader.END_ELEMENT
                                    && "adestrador".equals(reader.getLocalName())) {
                                break;
                            }
                        }

                        listaAdestradores.add(
                                new Adestrador(id, nome, nacemento)
                        );
                    }
                }
            } finally {
                reader.close();
            }

        } catch (IOException
                 | XMLStreamException
                 | NumberFormatException
                 | DateTimeParseException e) {

            throw new RuntimeException(
                    "Erro ao ler o XML dos adestradores",
                    e
            );
        }

        return listaAdestradores;
    }
}
