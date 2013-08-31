package com.hazelcast.collection.list;

import com.hazelcast.collection.CollectionDataSerializerHook;
import com.hazelcast.collection.operation.CollectionOperation;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.spi.BackupOperation;

import java.io.IOException;

/**
 * @ali 8/31/13
 */
public class AddBackupOperation extends CollectionOperation implements BackupOperation {

    long itemId;

    Data value;

    public AddBackupOperation() {
    }

    public AddBackupOperation(String name, long itemId, Data value) {
        super(name);
        this.itemId = itemId;
        this.value = value;
    }

    public void beforeRun() throws Exception {

    }

    public void run() throws Exception {
        getOrCreateListContainer().addBackup(itemId, value);
    }

    public void afterRun() throws Exception {

    }

    public int getId() {
        return CollectionDataSerializerHook.ADD_BACKUP;
    }

    protected void writeInternal(ObjectDataOutput out) throws IOException {
        super.writeInternal(out);
        out.writeLong(itemId);
        value.writeData(out);
    }

    protected void readInternal(ObjectDataInput in) throws IOException {
        super.readInternal(in);
        itemId = in.readLong();
        value = new Data();
        value.readData(in);
    }
}
