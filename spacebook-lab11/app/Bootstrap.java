//import java.util.List;

//import play.*;
//import play.jobs.*;
//import play.test.*;
 
//import models.*;
 
//@OnApplicationStart
//public class Bootstrap extends Job 
//{ 
  //public void doJob()
  //{
    //Fixtures.deleteDatabase();
    //Fixtures.loadModels("data.yml");
  //}
//}

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import play.*;
import play.db.jpa.Blob;
import play.jobs.*;
import play.libs.MimeTypes;
import play.test.*;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job 
{ 
  public void doJob() throws FileNotFoundException
  { 
    Fixtures.deleteDatabase();
    Fixtures.loadModels("data.yml");
    
    String photoName1 = "public/images/Homer.jpg";
    Blob blob1 = new Blob();
    blob1.set(new FileInputStream(photoName1), MimeTypes.getContentType(photoName1));
    User homer = User.findByEmail("homer@simpson.com");
    homer.profilePicture = blob1;
    homer.save();
    
    String photoName2 = "public/images/marge.png";
    Blob blob2 = new Blob();
    blob2.set(new FileInputStream(photoName2), MimeTypes.getContentType(photoName2));
    User marge = User.findByEmail("marge@simpson.com");
    marge.profilePicture = blob2;
    marge.save();
    
    String photoName3 = "public/images/Bart.jpg";
    Blob blob3 = new Blob();
    blob3.set(new FileInputStream(photoName3), MimeTypes.getContentType(photoName3));
    User bart = User.findByEmail("bart@simpson.com");
    bart.profilePicture = blob3;
    bart.save();
    
    String photoName4 = "public/images/Lisa.png";
    Blob blob4 = new Blob();
    blob4.set(new FileInputStream(photoName4), MimeTypes.getContentType(photoName4));
    User lisa = User.findByEmail("lisa@simpson.com");
    lisa.profilePicture = blob4;
    lisa.save();
    
    String photoName5 = "public/images/Barney.png";
    Blob blob5 = new Blob();
    blob5.set(new FileInputStream(photoName5), MimeTypes.getContentType(photoName5));
    User barney = User.findByEmail("barney@gumble.com");
    barney.profilePicture = blob5;
    barney.save();
    
    String photoName6 = "public/images/Moe.png";
    Blob blob6 = new Blob();
    blob6.set(new FileInputStream(photoName6), MimeTypes.getContentType(photoName6));
    User moe = User.findByEmail("moe@moes.com");
    moe.profilePicture = blob6;
    moe.save();
  }
}