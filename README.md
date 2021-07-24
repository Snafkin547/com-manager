# logi-ops

logi-ops is a user-friendly tool for logistics managers who are responsible for decision makings for cargoes allocations to transportations and storages.

Users can register cargoes information such as type of items, size, delivery/expiriy date, transportations in their hand like ships and trcks, including their costs per ton, and storage capacities and costs. 
As you can see even by far, there are many variables in one string of logistics chain. 
It would expectedly be a lot more complicated as the numbers of chains grow and users have more optionalities.

logi-ops is to serve for such needs by handy application with the following key functions

##
### Key Functions
The following functions will be deployed. Users can use this app in the numbered sequence: register logistics elements, manage database, and run the optimization algorithm 

#### 1) Registration 
   This function is the data entry point for users to create the database. Users can input the following information:
   - Ship Information (Name, Size, ETA, Cost, Status) : [Ship Class](https://github.com/Snafkin547/logi-ops/blob/351918292e8e8bc30a5e5a9aff9a3ebb563af90d/src/cpw/Ship.java#L3)
   - Truck Information (Name, Size, ETA, Cost, Status) : To be Constructed
   - Cargo Information (Item, Size, Cost, Expiry, Status) : [Cargo Class](https://github.com/Snafkin547/logi-ops/blob/351918292e8e8bc30a5e5a9aff9a3ebb563af90d/src/cpw/Cargo.java#L3)
   - Storage Information (Name, Capacity, Location, Cost, Acceptable Items, Status) : [Storage] (https://github.com/Snafkin547/logi-ops/blob/483f990c4fe8cda8742c8a2769e04768cf147e8b/src/cpw/Storage.java#L6)

#### 2) Database Search
   This function is to manage/view users' database. Users can set query criteria, retrieve relevant dataset, and view/modify it
   - Ship Search (By Name, Size, ETA, Cost, Availability) : [dbOrganizer Class](https://github.com/Snafkin547/logi-ops/blob/351918292e8e8bc30a5e5a9aff9a3ebb563af90d/src/cpw/dbOrganizer.java#L5)
   - Truck Search (By Name, Size, ETA, Cost, Availability) : To be Constructed
   - Cargo Search (By Item, Size, Cost, Expiry, Availability) : To be Constructed
   - Storage Search (By Name, Capacity, ETR, Cost, Acceptable Items, Availability) : To be Constructed

####  3) Logistics optimizer
   This function optimizes logistics combinations of Ships, Trucks, Cargoes, and Storages. Users can calibrate and find the best match in the whole open-logistics positions, or just some part of the positions, like linking only ships and cargoes that are available and need to be sent within this months.
   - Whole logistics Optimizer : To be Constructed
   - Cargoes - Ships Optimizer : [CargoMatching Class](https://github.com/Snafkin547/logi-ops/blob/351918292e8e8bc30a5e5a9aff9a3ebb563af90d/src/cpw/CargoMatching.java#L6)
   - Cargoes/Ships - Storage Optimizer : To be Constructed
   - Cargoes/Storage - Truck Optimizer : To be Constructed

