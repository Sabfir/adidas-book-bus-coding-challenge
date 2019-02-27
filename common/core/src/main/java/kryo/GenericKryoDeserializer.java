package kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
public class GenericKryoDeserializer<T> implements Deserializer<T> {
    private final Class<T> clazz;

    public GenericKryoDeserializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // do nothing
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        Kryo kryo = new Kryo();
        try (Input input = new Input(data)) {
            Object result = kryo.readClassAndObject(input);
            if (result == null) {
                return null;
            } else {
                return operateClassType(result);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private T operateClassType(Object result){
        if (clazz != null) {
            if (!clazz.isInstance(result)) {
                log.warn("Deserialized object is of class {} not assignable to class {}",
                        result.getClass().getCanonicalName(), clazz.getCanonicalName());
                throw new ClassCastException("Kryo deserializer got an object of a wrong class");
            }
            return clazz.cast(result);
        }
        return (T) result;
    }

    @Override
    public void close() {
        // do nothing
    }
}
