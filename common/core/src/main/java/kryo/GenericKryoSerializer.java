package kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class GenericKryoSerializer<T> implements Serializer<T> {
    private static final int OUTPUT_STREAM_SIZE = 256;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // do nothing
    }

    @Override
    public byte[] serialize(String topic, T data) {
        try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(OUTPUT_STREAM_SIZE)) {
            Kryo kryo = new Kryo();
            final Output output = new Output(outputStream);
            kryo.writeClassAndObject(output, data);
            output.flush();
            final byte[] bytes = outputStream.toByteArray();
            output.close();
            return bytes;
        } catch (IOException e) {
            throw new SerializationException("Failed Serialization", e);
        }
    }

    @Override
    public void close() {
        // do nothing
    }
}
