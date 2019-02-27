package kryo;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

public class KryoSerdeFactory {

    public <T> Serializer<T> getPooledSerializer() {
        return new GenericKryoSerializer<>();
    }

    public <T> Deserializer<T> getPooledDeserializer(Class<T> clazz) {
        return new GenericKryoDeserializer<>(clazz);
    }
}
