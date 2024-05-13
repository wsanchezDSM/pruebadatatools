package com.prueba.datatools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.*;
import com.prueba.datatools.entities.DatosUsuario;
import com.prueba.datatools.models.DatosUsuarioDTO;
import com.prueba.datatools.service.DatosUsuarioService;

@Controller
public class ThymeleafController {

	@Autowired
	private DatosUsuarioService datosPersonalesService;

	@GetMapping("/crear")
	public String mostrarFormularioCrear(Model model) {
	   model.addAttribute("datosPersonales", new DatosUsuario());
	   return "crear";
	}

	@PostMapping("/crear")
	public String crearDatosPersonales(@ModelAttribute("datosPersonales") DatosUsuarioDTO datosPersonales) {
	    datosPersonalesService.guardarDatos(datosPersonales);
	    return "redirect:/leer";
	}

	@GetMapping("/leer")
	public String listarDatosPersonales(Model model) {
	    List<DatosUsuarioDTO> datos = datosPersonalesService.obtenerDatosPlantilla();
	    model.addAttribute("datos", datos);
	    return "leer";
	 }

	@GetMapping("/editar/{identificacion}")
    public String mostrarFormularioActualizar(@PathVariable("identificacion") String identificacion, Model model) {
        DatosUsuarioDTO datosPersonales = datosPersonalesService.obtenerDatosIdentificacion(identificacion);
        model.addAttribute("datosPersonales", datosPersonales);
        return "actualizar";
    }

    @PostMapping("/actualizar/{identificacion}")
    public String actualizarDatosPersonales(@PathVariable("identificacion") String identificacion, @ModelAttribute("datosPersonales") DatosUsuarioDTO datosPersonales) {
        datosPersonalesService.actualizarDatosPlantilla(identificacion, datosPersonales);
        return "redirect:/leer";
    }

    @GetMapping("/eliminar/{identificacion}")
    public String mostrarConfirmacionEliminar(@PathVariable("identificacion") String identificacion, Model model) {
        DatosUsuarioDTO datosPersonales = datosPersonalesService.obtenerDatosIdentificacion(identificacion);
        model.addAttribute("datosPersonales", datosPersonales);
        return "eliminar";
    }

    @PostMapping("/eliminar/{identificacion}")
    public String eliminarDatosPersonales(@PathVariable("identificacion") String identificacion) {
        datosPersonalesService.eliminarDatos(identificacion);
        return "redirect:/leer";
    }

	

}
