package security;

import data.Data;

public interface ISecurityService {
    void checkData(Data data) throws InvalidDataException, CriticalDataException;
}
