package factorydesign.apifactory;

import factorydesign.*;
import factorydesign.apiclasses.*;
import factorydesign.apiinterface.ApiTest;

public class ApiFactory
{
    public ApiTest Request(String type)
    {
        if (type.equals("Get"))
        {
            return new Get();
        }
        else  if (type.equals("Post"))
        {
            return new Post();
        }
        else  if (type.equals("Put"))
        {
            return new Put();
        }
        else  if (type.equals("Patch"))
        {
            return new Patch();
        }
        else  if (type.equals("Delete"))
        {
            return new Delete();
        }
        else  if (type.equals("BasicAuth"))
        {
            return new BasicAuth();
        }
        else  if (type.equals("BearerToken"))
        {
            return new BearerToken();
        }
        else  if (type.equals("DigestAuth"))
        {
            return new DigestAuth();
        }
        else  if (type.equals("APIKey"))
        {
            return new APIKey();
        }
        else  if (type.equals("OAuth2"))
        {
            return new OAuth2();
        }
        else  if (type.equals("Preemptive"))
        {
            return new Preemptive();
        }
        else

        {
            throw new IllegalArgumentException("Invalid Request type: " + type);
        }
    }
}
