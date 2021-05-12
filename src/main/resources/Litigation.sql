/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 5.5.62 : Database - litigation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`litigation` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `litigation`;

/*Table structure for table `cattregister` */

DROP TABLE IF EXISTS `cattregister`;

CREATE TABLE `cattregister` (
  `CATID` bigint(255) NOT NULL AUTO_INCREMENT,
  `FileNumber` varchar(100) NOT NULL,
  `FileYear` int(50) NOT NULL,
  `FiledBy` varchar(2000) NOT NULL,
  `CaseType` varchar(1000) NOT NULL,
  `CaseNumber` varchar(5000) NOT NULL,
  `YearOfFiling` int(100) DEFAULT NULL,
  `MinistryID` int(100) DEFAULT NULL,
  `DepartmentID` int(100) DEFAULT NULL,
  `CounselID` int(100) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Remarks` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`CATID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cattregister` */

/*Table structure for table `counseldetails` */

DROP TABLE IF EXISTS `counseldetails`;

CREATE TABLE `counseldetails` (
  `CounselID` bigint(255) NOT NULL AUTO_INCREMENT,
  `Title` varchar(5) NOT NULL,
  `Name` varchar(500) NOT NULL,
  `CounselTypeID` int(10) NOT NULL,
  `TermFrom` date NOT NULL,
  `TermUpto` date NOT NULL,
  `Address` varchar(2000) NOT NULL,
  `MobileNumber` varchar(35) DEFAULT NULL,
  `EmailID` varchar(100) DEFAULT NULL,
  `TelephoneNumber` varchar(12) DEFAULT NULL,
  `CourtID` int(10) NOT NULL,
  `Status` tinyint(4) NOT NULL DEFAULT '1',
  `Remarks` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`CounselID`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=latin1;

/*Data for the table `counseldetails` */

insert  into `counseldetails`(`CounselID`,`Title`,`Name`,`CounselTypeID`,`TermFrom`,`TermUpto`,`Address`,`MobileNumber`,`EmailID`,`TelephoneNumber`,`CourtID`,`Status`,`Remarks`) values 
(1,'Shri','M B Nargund\r\n',1,'2019-12-19','2022-12-18','No. 247, Medha, 53rd Cross, Opp. Srirama Mandir, 4th Block, Rajajinagar, Bangalore - 560 010\r\n','8023151563','mbnnargund@rediffmail.com; asgi-hckar@indianjudiciary.gov.in\r\n',NULL,1,1,NULL),
(2,'Shri','C Shashikantha\r\n',2,'2018-05-24','2021-05-23','Dwaraka, 84/4, 6th Cross, 2nd Stage, KPHB Colony, Basaveshwaranagar,Bangalore-560 079\r\n','9845547979','shashikantha.c@gmail.com\r\n','080-23311174',1,1,NULL),
(3,'Smt','K Sarojini Muthana\r\n',3,'2018-03-20','2021-03-19','# 9, Kalengada, 30th Cross, Tilaknagar Main Road, Jayanagar, Bangalore-560 041\r\n','9886128238','ksarojini@yahoo.com\r\n','080-22456854',1,1,NULL),
(4,'Shri','V C Jagannath\r\n',3,'2018-03-20','2021-03-19','#24, R V Layout, Kumara Park West, Bangalore-560020\r\n','9448615325','vc_jagan@yahoo.com\r\n',NULL,1,1,NULL),
(5,'Shri','A D Vijaya Havanur\r\n',3,'2018-03-20','2021-03-19','#106/66, Surabhi, 43rd Cross, 6th Main, V Block Jayanagar, Bangalore - 560041\r\n','9900747590','vijayahavanur@gmail.com\r\n',NULL,1,1,NULL),
(6,'Shri','K Aparjitha Ariga\r\n',3,'2018-03-20','2021-03-19','# 47, Canara Bank Out House, Nandi Durga Road,Jaya Mahal Extension, Bangalore - 56\r\n','9845035052','ariga6488@gmail.com\r\n','080-23430921',1,1,NULL),
(7,'Shri','P Karunakar\r\n',3,'2018-03-20','2021-03-19','# A-21, Legacy Celino Apartments, Dasarahalli, Hebbal Kempapura, Bangalore - 560024\r\n','9448068275','pkaranadvocate@gmail.com\r\n','080-22340576',1,1,NULL),
(8,'Shri','Madhukar Deshpande\r\n',3,'2018-03-20','2021-03-19','# S2, T3 & T4, 27/1, Shalimar Plaza, 10th Cross, Swimming Pool Extension, Palace Guttahalli Main Road, Malleshwaram, Bangalore - 560 003\r\n','9448250300','madhuremadhu@gmail.com\r\n','080-23560156',1,1,NULL),
(9,'Shri','M Unnikrishnan',3,'2018-03-20','2021-03-19','#46, 1st A Cross, AECS Layout Road, Near ISRO, RMV, 2nd Stage, Bangalore - 560094\r\n','9844138209','unniblr@gmail.com\r\n','080-23418399',1,1,NULL),
(10,'Shri','Shivaputrappa Basappa Pavin\r\n',3,'2018-03-20','2021-03-19','# 143/41/A, 20th Cross, Bhagavathi Layout, Near Bhagavathi Temple, Hulimavu, Bangalore - 560076\r\n','9886104434',NULL,NULL,1,1,NULL),
(11,'Shri','Sudesh Kumar Acharya\r\n',3,'2018-03-20','2021-03-19','734/37, 1st Floor, 1st Cross, 1st Main, Vyalikaval, Bangalore - 560003\r\n','9844096710','skacharya62@gmail.com\r\n',NULL,1,1,NULL),
(12,'Shri','D Basavaraja\r\n',3,'2018-03-20','2021-03-19','Flat No A-204, 2nd Floor, A Block, Century Marvel Apartment, Opposite to Sindhi College, Hebbal-Kempapura, HA Farm Post,Bangalore-560 024\r\n','9449849396','dbasavaraja57@gmail.com\r\n',NULL,1,1,NULL),
(13,'Smt','M R Vanaja\r\n',3,'2018-03-20','2021-03-19','# 104, 4th Cross, M.L.A Layout, RMV II Stage Ext. Bhoopasandra, Bangalore - 560094\r\n','9449270768','mudambivanaja@gmail.com\r\n','080-23415321',1,1,NULL),
(14,'Shri','A Chandrachud\r\n',3,'2018-03-20','2021-03-19','# 63, 4th Main, 6th Cross, Chamarajpet, Bangalore - 560018\r\n','9845088923','chandrachud63@gmail.com\r\n',NULL,1,1,NULL),
(15,'Shri','Premkumar\r\n',4,'2018-03-20','2021-03-19','Off No. 122, Mahesh Market, 3rd Floor, Next to Udupi krishna Bhavan Hotel, Balapet Main Road, Balapet, Bangalore - 560053\r\n','9342843626; 7019423227\r\n','premnbms@gmail.com\r\n',NULL,1,1,NULL),
(16,'Smt','Manjuladevi R Kamadolli\r\n',4,'2018-03-20','2021-03-19','# 101, Skanashree-5, S.L.V Layout, Mysuru Road, Opp. R.R. Arch, Nayandahalli, Bangalore - 560039\r\n','9448456621\r\n','manjulakamadolli@gmail.com\r\n',NULL,1,1,NULL),
(17,'Smt','S Sumathi\r\n',4,'2018-03-20','2021-03-19','# 4, Ksheera Sagara, 2nd Main, Srirampuram, Bangalore - 560021\r\n','9741412669\r\n',NULL,NULL,1,1,NULL),
(18,'Shri','Sharaschandra R Dodawad\r\n',4,'2018-03-20','2021-03-19','# 4 (old No. 15/2), I Main Jedahalli, V Block Rajajinagar (Beside Maduramma Temple) Bangalore - 560010\r\n','9986514066\r\n','sharathrd@gmail.com\r\n',NULL,1,1,NULL),
(19,'Shri','R Madanan Pillai',4,'2018-03-20','2021-03-19','#8, Adithya, 2nd Main, 3rd Stage, A.E.C.S Layout, Sanjay Nagar Post, Bangalore - 560094\r\n','9449682210\r\n','chenkur@gmail.com\r\n',NULL,1,1,NULL),
(20,'Ms','M Birdy Aiyappa\r\n',4,'2018-03-20','2021-03-19','13/25, City Point, TF 4, III Floor, Infantry Road, Bangalore - 560001\r\n','9845190548\r\n','birdyaiyappa@gmail.com\r\n',NULL,1,1,NULL),
(21,'Smt','Anupama Hegde\r\n',4,'2018-03-20','2021-03-19','Flat No. 304, Sumukha Emerald, No. 789, 6th Cross, Kothnur Main Road, J.P Nagar, 8th Phase, Bangalore-560076\r\n','9448445131\r\n','anuadv.hegde@gmail.com\r\n',NULL,1,1,NULL),
(22,'Shri','K S Bheemaiah\r\n',4,'2018-03-20','2021-03-19','No.186/1, 2nd Floor, J C Complex Annexe, Sirur Park Road, Sheshadripuram, Bangalore - 560 020\r\n','9845339307\r\n','ks_bheemaiah@yahoo.in\r\n','080-23468379',1,1,NULL),
(23,'Smt','K S Anasuyadevi\r\n',4,'2018-03-20','2021-03-19','# F2, 1st Floor, Aarshitha Nilaya, 2nd Cross, Opp. Canara Bank, Dasarahalli Main Road, Bhuvaneshwari Nagar, Bangalore - 560024\r\n','9901541549\r\n','anasuyadeviks@yahoo.com\r\n',NULL,1,1,NULL),
(24,'Shri','Jagadish Baliga Naikaup\r\n',4,'2018-03-20','2021-03-19','# 22, 2nd Floor, Nehru Nagar Main Road, Sheshadripuram, Bangalore - 560 020\r\n','9448332327\r\n','advocatebaliga@gmail.com\r\n',NULL,1,1,NULL),
(25,'Shri','G D Srinivas\r\n',4,'2018-03-20','2021-03-19','off. # 3327, 7th Cross, 13th Main, Hal II Stage, Indiranagar, Bangalore - 560008\r\n','7019215236; 9844256470\r\n','gdsenu@gmail.com\r\n',NULL,1,1,NULL),
(26,'Shri','B Pramod\r\n',4,'2018-03-20','2021-03-19','# 44, Shivakrupa, II Cross, Malleswaram, Bangalore - 560003\r\n','9880436013\r\n','pramod.advocate@gmail.com\r\n',NULL,1,1,NULL),
(27,'Shri','Gururaj Yadravi\r\n',4,'2018-03-20','2021-03-19','# 21, Flat No. 001, 1st Floor, U P Arcade, 18th Cross, 8th Main, Malleshwaram, Near Fast Food Hotel, Bangalore - 560055\r\n','9986534097\r\n','gururaj.yadravi@gmail.com\r\n',NULL,1,1,NULL),
(28,'Smt','R Madhavi',4,'2018-03-20','2021-03-19','No. 746/5, 9th Cross, M C Layout, Vijayanagar, Bangalore - 560 040\r\n','9538615959; 9035405952; 7899045697\r','madhavi.airfly@yahoo.co.in\r\n',NULL,1,1,NULL),
(29,'Shri','Shivananda D S',4,'2018-03-20','2021-03-19','# 217/A, 57th Cross, 4th Block, Rajajinagar, Bangalore - 560010\r\n','9845616898\r\n','shivananda692014@gmail.com\r\n',NULL,1,1,NULL),
(30,'Shri','Aditya Singh\r\n',4,'2018-03-20','2021-03-19','C/o Mrs Parvathi Ashok, # 125, 2nd Floor, 6th Cross, Bapuji Layout, Vijayanagar, Near KFC Chandra Layout, Bangalore - 560040\r\n','8951104125; 9730400001\r\n','adv.adisinqh@gmail.com\r\n',NULL,1,1,NULL),
(31,'Smt','Prema Hatti\r\n',4,'2018-03-20','2021-03-19','Siddhi Sadan, # 67, Serpentine Road, Kumara Park West , Bangalore - 560020\r\n','9945428411\r\n','hattiprema@gmail.com\r\n',NULL,1,1,NULL),
(32,'Shri','Siji Malayil\r\n',4,'2018-03-20','2021-03-19','S-510, 5th Floor, South Block, Manipal Centre, M G Road, Bangalore - 560 042\r\n','9980554360; 9844104360\r\n','smalayiladvocates@gmail.com\r\n','080-42177151',1,1,NULL),
(33,'Shri','H Jayakara Shetty\r\n',4,'2018-03-20','2021-03-19','# 14, 19th Cross, Bhuvaneshwari Nagar, Hebbal Dasarahalli Main Road, Bangalore - 560024\r\n','9845500302; 8762918462\r\n','hjshetty94@gmail.com\r\n',NULL,1,1,NULL),
(34,'Shri','Laxminarayan N Hegde\r\n',4,'2018-03-20','2021-03-19','# 520, 1st Floor, 53rd Cross, Rajajinagar, Bangalore - 560010\r\n','9448532978\r\n','laxminarayannhegde@gmail.com\r\n',NULL,1,1,NULL),
(35,'Shri','Chennakeshava B S\r\n',4,'2018-03-20','2021-03-19','# 41/2, 14th Cross, 2nd Stage, Indiranagar, Bangalore - 560038\r\n','9886672352\r\n','chenna.bs@gmail.com\r\n',NULL,1,1,NULL),
(36,'Shri','S Rajashekar\r\n',4,'2018-03-20','2021-03-19','No. 4009, Ground Floor, High Point IV, Basaveshwara Circle, Near Hotel Chalukya, Palace Road, Bangalore - 560001\r\n','9448725919; 9880597916\r\n','rajadvo@gmail.com\r\n',NULL,1,1,NULL),
(37,'Shri','Timmanna Bhat\r\n',4,'2018-03-20','2021-03-19','Devathe No. 452, ESI Hospital Road, 41st Cross, 2nd Block, Rajajinagar, Bangalore - 560010\r\n','9448729204\r\n','tb.devathelawyers@gmail.com\r\n',NULL,1,1,NULL),
(38,'Shri','Basavaraj Pujar S',4,'2018-03-20','2021-03-19','N.P.Singri, # 213/3, Sridharkrupa, Annadaneswara Nilaya, 56th Cross, 4th Block, Rajajinagar, Bangalore - 560010\r\n','9945339880; 9019305614\r\n','bpoojars@gmail.com\r\n',NULL,1,1,NULL),
(39,'Shri','Jagadish G Kumbar\r\n',4,'2018-03-20','2021-03-19','# 51, 1st Floor, 2nd Cross, 5th Main, Binny Mill Road Cross, C P V Block, Ganganagar Ext, R T Nagar Post, Bangalore - 560032\r\n','9448265534\r\n','jagadishgkumbar@gmail.com\r\n',NULL,1,1,NULL),
(40,'Shri','A D Ramananda\r\n',4,'2018-03-20','2021-03-19','# 231, 53rd B Cross, 17th D Main, 3rd Block, Rajajinagar, Bangalore - 560010\r\n','9845216402\r\n','adramananda@yahoo.co.in\r\n',NULL,1,1,NULL),
(41,'Shri','Rajesh Rai K',4,'2018-03-20','2021-03-19','# 102, 1st Floor, Comfort Ganga Lakshmi, New No.10, 5th Cross Wilson Garden, Bangalore - 560027\r\n','9448040127\r\n',NULL,NULL,1,1,NULL),
(42,'Shri','A P Pulakeshi\r\n',4,'2018-03-20','2021-03-19','# 30/64, 12th Main, 3rd Block East, Jayanagar, Bangalore - 560011\r\n','9844523242\r\n','pulakeshi2007@gmail.com\r\n',NULL,1,1,NULL),
(43,'Shri','Anandarama K',4,'2018-03-20','2021-03-19','Off. No.201, 2nd Floor, Steerwell Chambers, #1, Kumara Park East, Bangalore - 560001\r\n','9845722170\r\n','anandramk@gmail.com\r\n',NULL,1,1,NULL),
(44,'Shri','Rajaram Sooryambail\r\n',4,'2018-03-20','2021-03-19','# 1, 2nd Floor, 2nd B Cross, Vasanthappa Block, CBI Road, Ganganagar, Bangalore - 560032\r\n','9845928053; 9663244666\r\n',NULL,NULL,1,1,NULL),
(45,'Smt','Irfana Nazeer\r\n',4,'2018-03-20','2021-03-19','Irfana Nazeer & Associates, 2nd Floor, 16/1, 1st Cross, CSI Compound, Mission Road, Bangalore - 560 027\r\n','9844022347\r\n','irfana@inandassociates.com\r\n',NULL,1,1,NULL),
(46,'Shri','Raghavendra K P',4,'2018-03-20','2021-03-19','# 11, S-24, 2nd Floor, 10th Cross, VMC Complex, Cubbonpet, Bangalore - 560002\r\n','9880183271; 8892275274\r\n','ragu.llb@gmail.com\r\n',NULL,1,1,NULL),
(47,'Smt','Budrannisa C B',4,'2018-03-20','2021-03-19','# 51, 4th Cross, Kanaka Nagar, R T Nagar (PO), Bangalore - 560032\r\n','9845037957\r\n','budrunnisaadvocate@gmail.com\r\n',NULL,1,1,NULL),
(48,'Smt','B M Nagaveena\r\n',4,'2018-03-20','2021-03-19','#203, Amar Towers, 2nd Floor, 1st Cross, Gandhinagar, Bangalore - 560009\r\n','9481671983; 9741808783\r\n','naguklore@gmail.com\r\n',NULL,1,1,NULL),
(49,'Shri','H S Suresh\r\n',4,'2018-03-20','2021-03-19','# 8, 8th I Cross, 14th Main, Attiguppe, Vijayanagar II Stage, Bangalore - 560040\r\n','9448162828\r\n','sureshhs43@gmail.com\r\n',NULL,1,1,NULL),
(50,'Shri','Shankar Nag G M\r\n',4,'2018-03-20','2021-03-19','# 201, Mamatha Apartment, II Floor, IV Main, Gandhinagar, Bangalore - 560009\r\n','9164978308\r\n','shankarnaggm@gmail.com\r\n',NULL,1,1,NULL),
(51,'Shri','Arun K S',4,'2018-03-20','2021-03-19','Vallari Associates, Advocate No. 1387, Ground Floor, 6th Cross, Ashok Nagar, BSK I Stage, Bangalore - 560050\r\n','9900313929\r\n','arunlawyer2010@yahoo.co.in\r\n',NULL,1,1,NULL),
(52,'Smt','D N Mamatha\r\n',4,'2018-03-20','2021-03-19','W/o Shri Rvi Kumar H.T.No160, Akshaya, 5th Cross, BHCS Layout, Uttarahalli, Behind Brindavan Apts, Bangalore - 560061\r\n','9845150549\r\n','dnmamtha@gmail.com\r\n',NULL,1,1,NULL),
(53,'Shri','Jeevan K',4,'2018-03-20','2021-03-19','# 14/1, 3rd Floor, 4th Cross, Gandhinagar, Bangalore - 560009\r\n','9844232066; 8762534316\r\n','jeevank_rai@yahoo.com\r\n',NULL,1,1,NULL),
(54,'Smt','L V Aparna\r\n',4,'2018-03-20','2021-03-19','# 343, 15th Cross, 11th Main, F Block, Sahakaranagara, Bangalore - 560092\r\n','9481828263\r\n','aparnalvreddy@gmail.com\r\n',NULL,1,1,NULL),
(55,'Shri','K Manjunatha Rao Bhonsle\r\n',4,'2018-03-20','2021-03-19','# 1, R.B Lane, Akkipet, Bangalore - 560053\r\n','9844183191\r\n','manjunathraobhonsle@gmail.com\r\n',NULL,1,1,NULL),
(56,'Shri','Rajashekaraiah\r\n',4,'2018-03-20','2021-03-19','674/A, 5th Cross, 7th Main, 2nd Stage, 11th Block, Nagarabhavi,Bangalore-560072\r\n','9980234253\r\n','vrajashekaraiah@gmail.com\r\n',NULL,1,1,NULL),
(57,'Shri','M S Nagaraja\r\n',4,'2018-03-20','2021-03-19','No. 231, Lakshmi Nivas 1st Floor, Subedhar Chatram Road, Gandhinagar, Bengaluru - 560009\r\n','9448161364; 8310975523\r\n','msnadvocate72@gmail.com\r\n',NULL,1,1,NULL),
(58,'Shri','Nagaraja H R',4,'2018-03-20','2021-03-19','Sriram Law Associates, 3rd Floor, # 55, Railway Parallel Road, Kumara Park East, Bangalore-560 020\r\n','9901994860; 8041464800\r\n','nagarajahegde@yahoo.com\r\n',NULL,1,1,NULL),
(59,'Smt','L Manjula\r\n',4,'2018-03-20','2021-03-19','# 90/2, VI Main, Thimma Reddy Road (Near Admiralty Square) Appa Reddy Palyam, HAL II Stage, Indiranagar, Bangalore - 560038\r\n','9844040704\r\n','advmanjula@gmail.com\r\n',NULL,1,1,NULL),
(60,'Shri','Athma V Hiremath\r\n',4,'2018-03-20','2021-03-19','# 177/9, 1st Floor, 12th Cross, Cubbonpet Main Road, Bangalore - 560002\r\n','9886507917\r\n','atma.ap@gmail.com\r\n',NULL,1,1,NULL),
(61,'Shri','B S Venkatanarayana\r\n',4,'2018-03-20','2021-03-19','T-4, Shalimar Plaza, Palace Guttahalli Main Road, Malleshwaram Bangalore - 560003\r\n','7090017888\r\n','bsviyer@gmail.com\r\n',NULL,1,1,NULL),
(62,'Shri','G A Srikante Gowda\r\n',4,'2018-03-20','2021-03-19','# 3216/62, 2nd Floor, Sarvasheela Complex, Bellary Main Road, (Near HP Petrol Bunk) Ganganagar,Bangalore - 560032\r\n','9448459525\r\n','kantegowda06@gmail.com\r\n',NULL,1,1,NULL),
(63,'Shri','Praveena Y Devareddiyavara\r\n',4,'2018-03-20','2021-03-19','# 20, 2nd Floor, 4th Cross, S.V.G. Nagar, Mudalapalya, Bangalore - 560072\r\n','9591737272\r\n','pydadvocate@gmail.com\r\n',NULL,1,1,NULL),
(64,'Shri','A Rajesh\r\n',4,'2018-03-20','2021-03-19','Veerabrahma Krupa, No.705, 5th Cross, Upkar Layout, Ullalu, Srigandadakavalu Post, Bengaluru - 560 091\r\n','9980928179; 9739950950\r\n','rajeshadv24@gmail.com\r\n',NULL,1,1,NULL),
(65,'Shri','K N Krishna Rao\r\n',4,'2018-03-20','2021-03-19','# 23/3, Hari Krishna Road, High Grounds, Bangalore - 560 001\r\n','9448073522\r\n','krishnaraoadv@gmail.com\r\n',NULL,1,1,NULL),
(66,'Smt','Jayashree G\r\n',4,'2018-03-20','2021-03-19','#45, 4th A Main, Govindaraja Nagar, Vijayanagar, Bangalore - 560040\r\n','9845396773; 9886165398\r\n','jyshr_srinivas@yahoo .com\r\n',NULL,1,1,NULL),
(67,'Shri','Avinash M Angadi\r\n',4,'2018-03-20','2021-03-19','# 13 Anugraha, Nader Park, Dharwad - 560052\r\n','9448657358\r\n','angadiavin@gmail.com\r\n',NULL,1,1,NULL),
(68,'Shri','P V Ravindranath\r\n',4,'2018-03-20','2021-03-19','# 27/17. 1st A Main, 1st N Block, Rajajinagar, Bangalore - 560010\r\n','9900102282\r\n','pvravindranath.1963@gmail.com\r\n',NULL,1,1,NULL),
(69,'Shri','H Mallangoud\r\n',4,'2018-03-20','2021-03-19','# 4933, 11th Floor, High Point IV, No. 45, Palace Road, Bangalore - 560001\r\n','9448142357\r\n','hmallanvqoud@gmail.com\r\n',NULL,1,1,NULL),
(70,'Shri','Ranjan Kumar K\r\n',4,'2018-03-20','2021-03-19','G-09, Sri Pearl Park Apartment, Jnanabharathi Layout III Stage, Opp. RVCE College, Mysore Road, Bangalore - 560059\r\n','9632819377\r\n','ranjankumark@yahoo.com\r\n',NULL,1,1,NULL),
(71,'Shri','Ananda K V',4,'2018-03-20','2021-03-19','# 122, 3rd Floor, 1st Main, 1st Cross, Beside Vishal Talkies, Ranganathapura, Kamakshipalya, Bangalore - 560079\r\n','9739872104\r\n','aanikv1989@gmail.com\r\n',NULL,1,1,NULL),
(72,'Shri','Timmappa Chandrashekar Naik\r\n',4,'2018-03-20','2021-03-19','# 380, 1st Main Rao, Panchsheel Nagar, Moodalapaalya Bangalore - 560072\r\n','9110481616; 9035693353\r\n','tushartc.naik@gmail.com\r\n',NULL,1,1,NULL),
(73,'Shri','Chidananda P',4,'2018-03-20','2021-03-19','# 15/1 A (Old No.601), 3rd Main (Opp. Swimming Pool), Sadashivanagar, Bangalore - 560080\r\n','9845112957\r\n','chidananda@tarkalegal.com\r\n',NULL,1,1,NULL),
(74,'Shri','Suresh Ganapati Hegde\r\n',4,'2018-03-20','2021-03-19','# 361, BSK, 1st Stage, Hanuman nagar, Bangalore - 560058\r\n','9901934492; 7259434492\r\n','santosh.hegde26@gmail.com\r\n',NULL,1,1,NULL),
(75,'Shri','S S Haveri\r\n',4,'2018-03-20','2021-03-19','# 2802, 37th Cross, East End Road, 9th Block, Jayanagar, Bangalore - 560069\r\n','9448084105\r\n','haverissraju@gmail.com\r\n',NULL,1,1,NULL),
(76,'Shri','Mahesh C M\r\n',4,'2018-03-20','2021-03-19','# 58, 2nd Floor, 4th Main, 8th Cross, Malleswaram, Bangalore - 560003\r\n','9448187118\r\n','Mahesh.cm4@gmail.com\r\n',NULL,1,1,NULL),
(77,'Shri','Hemanth R Rao\r\n',4,'2018-03-20','2021-03-19','# 14, S.K.Prerana, 2nd Floor, Nagappa Road, Seshadripuram, Bangalore - 560020\r\n','8017421049\r\n','hemanth@hsrandassociates.com\r\n; hemanthrrao@outlook.com',NULL,1,1,NULL),
(78,'Shri','Shivakumar S\r\n',4,'2018-03-20','2021-03-19','# 403, Andes Block, Heritage Estate Apartments, Doddaballapura Road, Yelahanka, Bangalore - 560064\r\n','9731000789\r\n','legaladvisorshiva@gmail.com \r\n',NULL,1,1,NULL),
(79,'Shri','Prasanth Kumar B P\r\n',4,'2018-03-20','2021-03-19','# 30, Sai Nilaya, Ground Floor, 3rd Cross, Friends Circle, Rajeshwari Nagar, Laggere, Bangalore - 560058\r\n','9880191494; 7795862353\r\n','appuadvocate@gmail.com\r\n',NULL,1,1,NULL),
(80,'Shri','L Chandra Shekar\r\n',4,'2018-03-20','2021-03-19','# 5c-312, 5th Cross, 3rd Main, OMBR Layout, Bhuvanagiri, Bangalore\r\n','9980433566\r\n','skadvocates@gmail.com\r\n',NULL,1,1,NULL),
(81,'Shri','B L Kumar\r\n',4,'2018-03-20','2021-03-19','# 277, Panchalingeshwara Nilaya, 1st Floor, 3rd Cross, 10th Main, Mathikere, Bangalore - 560054\r\n','9980989455\r\n',NULL,NULL,1,1,NULL),
(82,'Shri','B M Kushalappa\r\n',4,'2018-03-20','2021-03-19','# 4(10/2), G Floor, West Park Road, Kumara Park West, Bangalore - 560001\r\n','9845128704; 9448976601\r\n','kushaladv@gmail.com\r\n',NULL,1,1,NULL),
(83,'Shri','Gowtham Dev C Ullal\r\n',4,'2018-03-20','2021-03-19','Room No. 1 & 2, 1st Floor, Champala Building, Vinayaka Circle, II MN Road, Palace Guttahalli, Bangalore - 560003\r\n','9448074451; 9686753106\r\n','gowthamullal@gmail.com\r\n',NULL,1,1,NULL),
(84,'Shri','Dhananjaya P S',4,'2018-03-20','2021-03-19','C/o Viyamba, No. 201/11, 2nd Floor, 7th Main, 7th A Cross, Hampinagara, RPC Layout, Bangalore - 560040\r\n','9945824150; 9742010720\r\n','lawyerdanips@gmail.com\r\n',NULL,1,1,NULL),
(85,'Smt','N Shashikala\r\n',4,'2018-03-20','2021-03-19','# 7/1, 2nd Floor,Mahalakshmi Building, Kumara Park East, West Park Road, Bangalore - 560001\r\n','7338214716\r\n','rmuthanna64@gmail.com\r\n',NULL,1,1,NULL),
(86,'Shri','Muniraju K\r\n',4,'2018-03-20','2021-03-19','Behind Vinayaka Temple, Madahalli, Virgonagar (P), Bengaluru East Taluk Bangalore - 560049\r\n','9731111171\r\n','mrk.advocates@gmail.com\r\n',NULL,1,1,NULL),
(87,'Smt','Renukaradhya R D\r\n',4,'2018-03-20','2021-03-19','# 136, 2nd Floor, Coconut Avenue Road, 8th Cross, Malleshwaram, Bangalore - 560003\r\n','9844386748\r\n',NULL,NULL,1,1,NULL),
(88,'Smt','Deepa Jayadeva\r\n',4,'2018-03-20','2021-03-19','# 45, D/o Jayadeva Beside Government School, Byrappanahalli, Near National Park, Bannerghatta(Post), Bangalore - 560083\r\n','9242419542; 9482013380\r\n','deepajayadeva@gmail.com\r\n',NULL,1,1,NULL),
(89,'Shri','M N Kumar',4,'2018-03-20','2021-03-19','# 60, Ground Floor, 2nd Main Road, 2nd Cross Road, Ramamohanapura, Bangalore - 560021\r\n','9448479917\r\n','kmn.studentoflaw@gmail.com\r\n',NULL,1,1,NULL),
(90,'Shri','V C Sudeep\r\n',4,'2018-03-20','2021-03-19','# 529, 11th A Cross, 3rd Block, BEL Layout, Vidyaranyapura, Bangalore - 560097\r\n','9900711113; 9900711119\r\n','vcsudeep@gmail.com; vcsudeep@legalminds.in\r\n',NULL,1,1,NULL),
(91,'Smt','Hema B',4,'2018-03-20','2021-03-19','# 14/3, 2nd Floor, Maharshi Aravindh Bhavan, Nrupatunga Road, Bangalore - 560001\r\n','9880077869\r\n',NULL,NULL,1,1,NULL),
(92,'Shri','Shubha S',4,'2018-03-20','2021-03-19','# 158/10, 3rd Main, 3rd Cross, Bank Colony, Chikkallasandra, Bangalore - 560061\r\n','9845303316\r\n','shubha.msjassociates@gmail.com\r\n',NULL,1,1,NULL),
(93,'Shri','Harsha P Banad\r\n',4,'2018-03-20','2021-03-19','# 7, 1st B Main Road, Bandappa Gardens, Bangalore - 560054\r\n','9900151022\r\n','harshabanad@gmail.com\r\n','080-23451122',1,1,NULL),
(94,'Shri','N Kumar\r\n',4,'2018-03-20','2021-03-19','# 1, 3rd Floor Amar Tower, 1st Cross, Gandhinagar, Bangalore - 560009\r\n','9845233150\r\n','kumarravut@gmail.com\r\n',NULL,1,1,NULL),
(95,'Shri','K Chandramohan\r\n',4,'2018-03-20','2021-03-19','R.K.Nilaya, # 8, Old No. 66/ B. M.T. Street, kodihalli Main Road, Indiranagar, 2nd Stage, Bangalore - 560008\r\n','9845202111\r\n','rkassociates05@gmail.com\r\n',NULL,1,1,NULL),
(96,'Shri','V Narasimha Holla\r\n',3,'2018-03-20','2021-03-19','# 17, Konarka, 7th C Main, Muthyalanagara, Bangalore - 560054\r\n','9448533047\r\n','hollansimha@gmail.com\r\n','080-23375870',2,1,NULL),
(97,'Shri','N Amaresh\r\n',3,'2018-03-20','2021-03-19','#25, Sriram, Kaveri Extension, Mariyannapalya, Bangalore - 560024\r\n','9449612093; 9902908909\r\n','amareshlaw@amail.com\r\n',NULL,2,1,NULL),
(98,'Shri','Vishnu Bhat\r\n',3,'2018-03-20','2021-03-19','# 13/1, Vasistha, 5th Main Road, Palace Guttahalli, Bangalore - 560003\r\n','9448669491\r\n','vishnubhatadvocate@gmail.com\r\n','080-23468046',2,1,NULL),
(99,'Shri','K Gajendra Vasu\r\n',3,'2018-03-20','2021-03-19','R K Nilaya, # 8, (old No. 66/B), M T Street, Kodihalli Main Road, Indiranagar, 2nd Stage, Bangalore - 560008\r\n','9845261965\r\n','gajendravasu@rediffmail.com\r\n',NULL,2,1,NULL),
(100,'Shri','M Vasudeva Rao\r\n',3,'2018-03-20','2021-04-02','# 003, Premier Residency, No.1, Lazor Road, Richards Town, Bangalore – 560 005\r\n','9019122209\r\n','mvrao.counsel@gmail.com\r\n','080-41735522',2,1,NULL),
(101,'Shri','H R Sreedhara\r\n',5,'2018-03-20','2021-03-19','# 24, 2nd Floor, 12th A Cross, 9th Main, Vayalikaval, Bangalore - 560003\r\n','9449344184\r\n','sreedharaheggodu@qmail.com\r\n',NULL,2,1,NULL),
(102,'Shri','Syed Murtuja Kazi\r\n',5,'2018-03-20','2021-03-19','D A 101, PWD Quarters, KHB Main Road, Kaval Byrasandra, R T Nagar Post, Bangalore\r\n','9900120155\r\n',NULL,NULL,2,1,NULL),
(103,'Shri','N B Patil\r\n',5,'2018-03-20','2021-03-19','# 7/1, Mallayya Complex, 5th Main, Near Kanti Sweets, Ganganagar, Bangalore - 560032\r\n','9448172851; 9972385054\r\n','nbpatiladv@qmail.com\r\n',NULL,2,1,NULL),
(104,'Shri','S Sugumaran\r\n',5,'2018-03-20','2021-03-19','Chaitanya Aprtments, # 40, Govindappa Road, Basavangudi, Bangalore - 560004\r\n','9845292802\r\n','sugumaran.adv@qmail.com\r\n','080-26622345',2,1,NULL),
(105,'Shri','Keshavmurthy H B\r\n',5,'2018-03-20','2021-03-19','# 555, Ground Floor, 53rd Cross, 3rd Block, Rajajinagar, Bangalore - 560010\r\n','9945564829\r\n',NULL,NULL,2,1,NULL),
(106,'Shri','Vijayakumaraswamy B G\r\n',5,'2018-03-20','2021-03-19','# 27, Park Road, Akkipete, Bangalore - 560053\r\n','9480464829\r\n',NULL,NULL,2,1,NULL),
(107,'Shri','K Dilip Kumar\r\n',5,'2018-03-20','2021-03-19','# 64/1, 2nd Floor, Lakshmi Complex, 1st Main, Shesharipuram\r\n',NULL,NULL,NULL,2,1,NULL);

/*Table structure for table `counseltype` */

DROP TABLE IF EXISTS `counseltype`;

CREATE TABLE `counseltype` (
  `CounselTypeID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CounselType` varchar(500) NOT NULL,
  PRIMARY KEY (`CounselTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `counseltype` */

insert  into `counseltype`(`CounselTypeID`,`CounselType`) values 
(1,'Additional Solicitor General'),
(2,'Assistant Solicitor General'),
(3,'Senior Panel Counsel'),
(4,'Central Government Counsel'),
(5,'Additional Central Government Standing Counsel');

/*Table structure for table `courtdetails` */

DROP TABLE IF EXISTS `courtdetails`;

CREATE TABLE `courtdetails` (
  `CourtID` int(255) NOT NULL AUTO_INCREMENT,
  `CourtName` varchar(1000) NOT NULL,
  `Location` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`CourtID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `courtdetails` */

insert  into `courtdetails`(`CourtID`,`CourtName`,`Location`) values 
(1,'High Court','Bangalore'),
(2,'Central Administrative Tribunal','Bangalore');

/*Table structure for table `departmentdetails` */

DROP TABLE IF EXISTS `departmentdetails`;

CREATE TABLE `departmentdetails` (
  `DepartmentID` bigint(255) NOT NULL AUTO_INCREMENT,
  `DepartmentName` varchar(1000) NOT NULL,
  `MinistryID` int(100) NOT NULL,
  `Address` varchar(5000) DEFAULT NULL,
  `TypeOfAddress` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`DepartmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;

/*Data for the table `departmentdetails` */

insert  into `departmentdetails`(`DepartmentID`,`DepartmentName`,`MinistryID`,`Address`,`TypeOfAddress`) values 
(1,'Agriculture, Cooperation & Farmers Welfare',1,NULL,NULL),
(2,'Agricultural Research & Education',1,NULL,NULL),
(3,'Chemicals & Petrochemicals',3,NULL,NULL),
(4,'Fertilizers',3,NULL,NULL),
(5,'Pharmaceuticals',3,NULL,NULL),
(6,'Commerce',6,NULL,NULL),
(7,'Promotion of Industry & Internal Trade',6,NULL,NULL),
(8,'Posts',7,NULL,NULL),
(9,'Telecommunications',7,NULL,NULL),
(10,'Consumer Affairs',8,NULL,NULL),
(11,'Food & Public Distribution',8,NULL,NULL),
(12,'Defence',11,NULL,NULL),
(13,'Defence Production',11,NULL,NULL),
(14,'Defence Research & Development',11,NULL,NULL),
(15,'Ex-Servicemen Welfare',11,NULL,NULL),
(16,'India Meteorological Department',13,NULL,NULL),
(17,'Economic Affairs',17,NULL,NULL),
(18,'Expenditure',17,NULL,NULL),
(19,'Financial Services',17,NULL,NULL),
(20,'Investment & Public Asset Management',17,NULL,NULL),
(21,'Revenue',17,NULL,NULL),
(22,'Animal Husbandry & Dairying',18,NULL,NULL),
(23,'Fisheries',18,NULL,NULL),
(24,'Health & Family Welfare',20,NULL,NULL),
(25,'Health Research',20,NULL,NULL),
(26,'Heavy Industry',21,NULL,NULL),
(27,'Public Enterprises',21,NULL,NULL),
(28,'Central Armed Police Forces',22,NULL,NULL),
(29,'Central Police Organization',22,NULL,NULL),
(30,'Border Management',22,NULL,NULL),
(31,'Home',22,NULL,NULL),
(32,'Internal Security',22,NULL,NULL),
(33,'Jammu & Kashmir Affairs',22,NULL,NULL),
(34,'Official Language',22,NULL,NULL),
(35,'States',22,NULL,NULL),
(36,'Higher Education',24,NULL,NULL),
(37,'School Education & Literacy',24,NULL,NULL),
(38,'Drinking Water & Sanitation',26,NULL,NULL),
(39,'Water Resources, River Development & Ganga Rejuvenation',26,NULL,NULL),
(40,'Justice',28,NULL,NULL),
(41,'legal Affairs',28,NULL,NULL),
(42,'legislative',28,NULL,NULL),
(43,'Administrative Reforms and Public Grievances',35,NULL,NULL),
(44,'Pension & Pensioner`s Welfare',35,NULL,NULL),
(45,'Personnel AND Training',35,NULL,NULL),
(46,'land Resources',40,NULL,NULL),
(47,'Rural Development',40,NULL,NULL),
(48,'Biotechnology',41,NULL,NULL),
(49,'Science & Technology',41,NULL,NULL),
(50,'Scientific & Industrial Research',41,NULL,NULL),
(51,'Empowerment of Persons WITH Disabilities',44,NULL,NULL),
(52,'Social Justice AND Empowerment',44,NULL,NULL),
(53,'Sports',51,NULL,NULL),
(54,'Youth Affairs',51,NULL,NULL),
(55,'Atomic Energy',52,NULL,NULL),
(56,'SPACE',52,NULL,NULL);

/*Table structure for table `highcourtregister` */

DROP TABLE IF EXISTS `highcourtregister`;

CREATE TABLE `highcourtregister` (
  `HighCourtID` bigint(255) NOT NULL AUTO_INCREMENT,
  `FileNumber` varchar(100) NOT NULL,
  `FileYear` int(50) NOT NULL,
  `FiledBy` varchar(2000) NOT NULL,
  `CaseType` varchar(1000) DEFAULT NULL,
  `CaseNumber` varchar(5000) DEFAULT NULL,
  `FRNumber` varchar(5000) DEFAULT NULL,
  `FRYear` int(100) DEFAULT NULL,
  `YearOfFiling` int(100) DEFAULT NULL,
  `MinistryID` int(100) DEFAULT NULL,
  `DepartmentID` int(100) DEFAULT NULL,
  `CounselID` int(100) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Remarks` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`HighCourtID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `highcourtregister` */

/*Table structure for table `ministrydetails` */

DROP TABLE IF EXISTS `ministrydetails`;

CREATE TABLE `ministrydetails` (
  `MinistryID` bigint(255) NOT NULL AUTO_INCREMENT,
  `MinistryName` varchar(1000) NOT NULL,
  `Address` varchar(5000) DEFAULT NULL,
  `TypeOfAddress` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MinistryID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

/*Data for the table `ministrydetails` */

insert  into `ministrydetails`(`MinistryID`,`MinistryName`,`Address`,`TypeOfAddress`) values 
(1,'Agriculture & Farmers Welfare',NULL,NULL),
(2,'AYUSH',NULL,NULL),
(3,'Chemicals & Fertilizers',NULL,NULL),
(4,'Civil Aviation',NULL,NULL),
(5,'Coal',NULL,NULL),
(6,'Commerce & Industry',NULL,NULL),
(7,'Communications',NULL,NULL),
(8,'Consumer Affairs, Food & Public Distribution',NULL,NULL),
(9,'Corporate Affairs',NULL,NULL),
(10,'Culture',NULL,NULL),
(11,'Defence',NULL,NULL),
(12,'Development of North Eastern Region',NULL,NULL),
(13,'Earth Sciences',NULL,NULL),
(14,'Electronics & Information Technology',NULL,NULL),
(15,'Environment, Forest & Climate Change',NULL,NULL),
(16,'External Affairs',NULL,NULL),
(17,'Finance',NULL,NULL),
(18,'Fisheries, Animal Husbandry & Dairying',NULL,NULL),
(19,'Food Processing Industries',NULL,NULL),
(20,'Health & Family Welfare',NULL,NULL),
(21,'Heavy Industries & Public Enterprises',NULL,NULL),
(22,'Home Affairs',NULL,NULL),
(23,'Housing & Urban Affairs',NULL,NULL),
(24,'Human Resources Development',NULL,NULL),
(25,'Information & Broadcasting',NULL,NULL),
(26,'Jal Shakti',NULL,NULL),
(27,'Labour & Employment',NULL,NULL),
(28,'Law & Justice',NULL,NULL),
(29,'Micro, Small & Medium Enterprises',NULL,NULL),
(30,'Mines',NULL,NULL),
(31,'Minority Affairs',NULL,NULL),
(32,'New & Renewable Energy',NULL,NULL),
(33,'Panchayati Raj',NULL,NULL),
(34,'Parliamentary Affairs',NULL,NULL),
(35,'Personal, Public Grievances & Pensions',NULL,NULL),
(36,'Petroleum & Natural Gas',NULL,NULL),
(37,'Power',NULL,NULL),
(38,'Railways',NULL,NULL),
(39,'Road Transport & Highways',NULL,NULL),
(40,'Rural Development',NULL,NULL),
(41,'Science & Technology',NULL,NULL),
(42,'Shipping',NULL,NULL),
(43,'Skill Development & Enterpreneurship',NULL,NULL),
(44,'Social Justice & Empowerment',NULL,NULL),
(45,'Statistics & Programme Implementation',NULL,NULL),
(46,'Steel',NULL,NULL),
(47,'Textiles',NULL,NULL),
(48,'Tourism',NULL,NULL),
(49,'Tribal Affairs',NULL,NULL),
(50,'Women & Child Development',NULL,NULL),
(51,'Youth Affairs & Sports',NULL,NULL),
(52,'NoMinistry',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
