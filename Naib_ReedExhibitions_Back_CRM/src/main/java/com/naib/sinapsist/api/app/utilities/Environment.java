package com.naib.sinapsist.api.app.utilities;

import java.nio.file.Path;
import java.nio.file.Paths;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Synapsist-Serv
 */
public class Environment {

	/**
	 * Define si el entorno de ejecución es de produccion o local.
	 */
	private static boolean isActiveProd = true;

	/**
	 * CROSS ORIGIN: Ruta de acceso para localhost en puerto por defecto de Angular.
	 */
	public static final String CORS_ANGULAR_DEFAULT = "http://localhost:4200";

	/**
	 * CROSS ORIGIN: Ruta de acceso para servidor apache
	 */
	public static final String CORS_ORIGIN_PROD = "https://sinapsist.mx";

	/**
	 * Método para acceder a recursos del calendario.
	 * 
	 * @param fileName Nombre del archivo + extensión.
	 * @return Path del archivo que se esta solicitando.
	 */
	public static final Path GET_SRC_CALENDARIO(String fileName) {
		if (isActiveProd) {
			return Paths.get("/home/projectNaib/evidenciasCalendario/" + fileName);
		} else {
			return Paths.get("evidenciasCalendario").resolve(fileName).toAbsolutePath();
		}
	}

	/**
	 * Método para acceder a recursos de evidencias de porcentaje de armado.
	 * 
	 * @param fileName Nombre del archivo + extensión.
	 * @return Path del archivo que se esta solicitando.
	 */
	public static final Path GET_SRC_EVIDENCIA_ARMADO(String fileName) {
		if (isActiveProd) {
			return Paths.get("/home/projectNaib/evidenciasArmado/" + fileName);
		} else {
			return Paths.get("evidenciasArmado").resolve(fileName).toAbsolutePath();
		}
	}

	/**
	 * Método para acceder a recursos de evidencias de servicios de stands.
	 * 
	 * @param fileName Nombre del archivo + extensión.
	 * @return Path del archivo que se esta solicitando.
	 */
	public static final Path GET_SRC_EVIDENCIA_SERVICIO(String fileName) {
		if (isActiveProd) {
			return Paths.get("/home/projectNaib/evidenciasServicio/" + fileName);
		} else {
			return Paths.get("evidenciasServicio").resolve(fileName).toAbsolutePath();
		}
	}

	/**
	 * Método para acceder a recursos de imágenes de expositores.
	 * 
	 * @param fileName Nombre del archivo + extensión.
	 * @return Path del archivo que se esta solicitando.
	 */
	public static final Path GET_SRC_IMG_EXPOSITOR(String fileName) {
		if (isActiveProd) {
			return Paths.get("/home/projectNaib/imgExpositor/" + fileName);
		} else {
			return Paths.get("imgExpositor").resolve(fileName).toAbsolutePath();
		}
	}

	/**
	 * Método para acceder a recursos de evidencias en chat de incidencias.
	 * 
	 * @param fileName Nombre del archivo + extensión.
	 * @return Path del archivo que se esta solicitando.
	 */
	public static final Path GET_SRC_EVIDENCIA_CHAT(String fileName) {
		if (isActiveProd) {
			return Paths.get("/home/projectNaib/evidenciasChat/" + fileName);
		} else {
			return Paths.get("evidenciasChat").resolve(fileName).toAbsolutePath();
		}
	}

	/**
	 * Método para acceder a recursos de imágenes de contacto de las empresas
	 * (expositores).
	 * 
	 * @param fileName Nombre del archivo + extensión.
	 * @return Path del archivo que se esta solicitando.
	 */
	public static final Path GET_SRC_IMG_EXPOSITOR_CONTACTO(String fileName) {
		if (isActiveProd) {
			return Paths.get("/home/projectNaib/imgContactoExpositor/" + fileName);
		} else {
			return Paths.get("imgContactoExpositor").resolve(fileName).toAbsolutePath();
		}
	}

	/**
	 * Método para acceder a recursos de chat de WhatsApp.
	 * 
	 * @param fileName Nombre del archivo + extensión.
	 * @return Path del archivo que se esta solicitando.
	 */
	public static final Path GET_SRC_CRM(String fileName) {
		if (isActiveProd) {
			return Paths.get("/home/projectNaib/uploadsCRM/" + fileName);
		} else {
			return Paths.get("uploadsCRM").resolve(fileName).toAbsolutePath();
		}
	}

	public static final Path GET_SRC_CRM_ARCHIVOS_EMAIL(String fileName) {
		if (isActiveProd) {
			return Paths.get("/home/projectNaib/uploadsCRM/archivosEmail/" + fileName);
		} else {
			return Paths.get("uploadsCRM/archivosEmail").resolve(fileName).toAbsolutePath();
		}
	}

	public static final Path GET_SRC_CRM_IMG_FIRMA(String fileName) {
		if (isActiveProd) {
			return Paths.get("/home/projectNaib/uploadsCRM/imgFirma/" + fileName);
		} else {
			return Paths.get("uploadsCRM/imgFirma").resolve(fileName).toAbsolutePath();
		}
	}

	public Environment() {
		Environment.isActiveProd = true;
	}

}
