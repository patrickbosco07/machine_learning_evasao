package br.com.tabelafipe.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;

import java.util.List;

public interface IConverteDado {
    public <T> T converteDado(String json, Class<T> classe);
    public <T> List<T> obterList(String json, Class<T> classe);
}
