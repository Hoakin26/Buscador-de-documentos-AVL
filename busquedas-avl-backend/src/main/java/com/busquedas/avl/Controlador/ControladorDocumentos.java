package com.busquedas.avl.Controlador;
import com.busquedas.avl.arbolAvl.ArbolAvl;
import com.busquedas.avl.busqueda.Estadistica;
import com.busquedas.avl.busqueda.MotorBusqueda;
import com.busquedas.avl.indexacion.Indexer;
import com.busquedas.avl.modelo.DireccionClass;
import com.busquedas.avl.modelo.DocumentoInfo;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/documentos")
public class ControladorDocumentos {

    private final ArbolAvl arbolAVL = new ArbolAvl();
    private final Estadistica estadistica = new Estadistica();
    private final Indexer indexer = new Indexer(arbolAVL);
    private final MotorBusqueda motorBusqueda = new MotorBusqueda(arbolAVL, estadistica);
    private final DireccionClass direccionArchivo = new DireccionClass();

    //ESCOGER UBICACION DE LOS DOCUMENTOS O ARCHIVOS
    @PostMapping
    public ResponseEntity<String> addDireccion(@RequestBody DireccionClass direccion) {
        try {
            indexer.obtenerDocumentos(new File(direccion.getDireccion()));
            direccionArchivo.setDireccion(direccion.getDireccion());
            System.out.println("Direccion: " + direccionArchivo.getDireccion());
            return ResponseEntity.ok("Indexación completada");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al indexar documentos");
        }
    }

    //OBTENER LA RUTA DEL ARCHIVO
    @GetMapping("/obtenerRuta")
    public ResponseEntity<DireccionClass> rutaArchivo() {
        System.err.println("MANDANDO RUTA: " + direccionArchivo.getDireccion());
        return ResponseEntity.ok(direccionArchivo);
    }

    //BUSQUEDA DE DOCUMENTOS POR PALABRAS CLAVE
    @GetMapping("/buscar/{palabra}")
    public ResponseEntity<List<DocumentoInfo>> buscarPalabra(@PathVariable String palabra) {
        System.out.println("ENTRANDO AL CONTROLADOR");
        List<DocumentoInfo> results = motorBusqueda.buscarPalabras(palabra);
        return ResponseEntity.ok(results);
    }

    //OBTENER TODOS LOS DOCUMENTOS
    @GetMapping
    public ResponseEntity<List<DocumentoInfo>> listaArchivos() {
        List<DocumentoInfo> docs = indexer.getDocumentos();
        return ResponseEntity.ok(docs);
    }

    //OBTENER LAS ESTADISTICAS
    @GetMapping("/obtenerEstadistica")
    public ResponseEntity<Estadistica> estadisticas() {
        return ResponseEntity.ok(estadistica);
    }

    //ABRIR EL DOCUMENTO WORD
    @PostMapping("/abrirWord")
    public ResponseEntity<String> abrirDocumento(@RequestBody DireccionClass direccion) {
        try {
            Runtime.getRuntime().exec("cmd /c start \"\" \"" + direccion.getDireccion() + "\"");
            return ResponseEntity.ok("Documento abierto");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al abrir el documento");
        }
    }
}
