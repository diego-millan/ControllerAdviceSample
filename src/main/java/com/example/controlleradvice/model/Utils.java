package com.example.controlleradvice.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globo.sales.commons.client.lang.InternalException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {

  @SafeVarargs
  public static <T>boolean checkParamIsNull(T... values){
    List<T> result = Arrays.stream(values).filter(Objects::nonNull).collect(Collectors.toList());

    return result.isEmpty();
  }

  public <T> T getFromJSON(String fileName, Class<T> clazz) {
    try {
      Path path = Paths.get("src", "test", "resources");
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      String absolutePath = path.toFile().getAbsolutePath();
      return objectMapper.readValue(new File(absolutePath + "/" +fileName), clazz);
    } catch (IOException e) {
      throw new InternalException("Error parsing JSON");
    }
  }
}
