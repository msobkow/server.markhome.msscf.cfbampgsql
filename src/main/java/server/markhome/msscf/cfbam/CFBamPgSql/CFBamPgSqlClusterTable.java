// Description: Java 11 PostgreSQL Jdbc DbIO implementation for Cluster.

/*
 *	server.markhome.msscf.CFBam
 *
 *	Copyright (c) 2020-2025 Mark Stephen Sobkow
 *	
 *
 *	Manufactured by MSS Code Factory 2.13
 */

package server.markhome.msscf.cfbam.CFBamPgSql;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.msscf.msscf.cflib.CFLib.*;
import server.markhome.msscf.cfsec.CFSec.*;
import server.markhome.msscf.cfint.CFInt.*;
import server.markhome.msscf.cfbam.CFBam.*;
import server.markhome.msscf.cfsec.CFSecObj.*;
import server.markhome.msscf.cfint.CFIntObj.*;
import server.markhome.msscf.cfbam.CFBamObj.*;

/*
 *	CFBamPgSqlClusterTable PostgreSQL Jdbc DbIO implementation
 *	for Cluster.
 */
public class CFBamPgSqlClusterTable
	implements ICFBamClusterTable
{
	private CFBamPgSqlSchema schema;
	protected PreparedStatement stmtReadBuffByPKey = null;
	protected PreparedStatement stmtReadBuffAll = null;
	protected PreparedStatement stmtPageBuffAll = null;
	protected PreparedStatement stmtLockBuffByPKey = null;
	protected PreparedStatement stmtCreateByPKey = null;
	protected PreparedStatement stmtUpdateByPKey = null;
	protected PreparedStatement stmtDeleteByPKey = null;
	protected PreparedStatement stmtReadAllBuff = null;
	protected PreparedStatement stmtReadBuffByIdIdx = null;
	protected PreparedStatement stmtReadBuffByUDomNameIdx = null;
	protected PreparedStatement stmtReadBuffByUDescrIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByUDomNameIdx = null;
	protected PreparedStatement stmtDeleteByUDescrIdx = null;
	protected PreparedStatement stmtSelectNextSecAppIdGen = null;
	protected PreparedStatement stmtSelectNextSecFormIdGen = null;
	protected PreparedStatement stmtSelectNextSecGroupIdGen = null;
	protected PreparedStatement stmtSelectNextHostNodeIdGen = null;
	protected PreparedStatement stmtSelectNextSecGroupFormIdGen = null;
	protected PreparedStatement stmtSelectNextSecGrpIncIdGen = null;
	protected PreparedStatement stmtSelectNextSecGrpMembIdGen = null;
	protected PreparedStatement stmtSelectNextServiceIdGen = null;

	public CFBamPgSqlClusterTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public int nextSecAppIdGen( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "nextSecAppIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_secappidgen("
			+		"?" + " ) as NextSecAppIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextSecAppIdGen == null ) {
				stmtSelectNextSecAppIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			int nextId;
			int argIdx = 1;
			stmtSelectNextSecAppIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextSecAppIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getInt( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextSecAppIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_secappidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public int nextSecAppIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecClusterPKey pkey = schema.getFactoryCluster().newPKey();
		pkey.setRequiredId( argId );
		int retval = nextSecAppIdGen( Authorization, pkey );
		return( retval );
	}

	public int nextSecFormIdGen( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "nextSecFormIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_secformidgen("
			+		"?" + " ) as NextSecFormIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextSecFormIdGen == null ) {
				stmtSelectNextSecFormIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			int nextId;
			int argIdx = 1;
			stmtSelectNextSecFormIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextSecFormIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getInt( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextSecFormIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_secformidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public int nextSecFormIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecClusterPKey pkey = schema.getFactoryCluster().newPKey();
		pkey.setRequiredId( argId );
		int retval = nextSecFormIdGen( Authorization, pkey );
		return( retval );
	}

	public int nextSecGroupIdGen( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "nextSecGroupIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_secgroupidgen("
			+		"?" + " ) as NextSecGroupIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextSecGroupIdGen == null ) {
				stmtSelectNextSecGroupIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			int nextId;
			int argIdx = 1;
			stmtSelectNextSecGroupIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextSecGroupIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getInt( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextSecGroupIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_secgroupidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public int nextSecGroupIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecClusterPKey pkey = schema.getFactoryCluster().newPKey();
		pkey.setRequiredId( argId );
		int retval = nextSecGroupIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextHostNodeIdGen( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "nextHostNodeIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_hostnodeidgen("
			+		"?" + " ) as NextHostNodeIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextHostNodeIdGen == null ) {
				stmtSelectNextHostNodeIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextHostNodeIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextHostNodeIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextHostNodeIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_hostnodeidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextHostNodeIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecClusterPKey pkey = schema.getFactoryCluster().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextHostNodeIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextSecGroupFormIdGen( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "nextSecGroupFormIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_secgroupformidgen("
			+		"?" + " ) as NextSecGroupFormIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextSecGroupFormIdGen == null ) {
				stmtSelectNextSecGroupFormIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextSecGroupFormIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextSecGroupFormIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextSecGroupFormIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_secgroupformidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextSecGroupFormIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecClusterPKey pkey = schema.getFactoryCluster().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextSecGroupFormIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextSecGrpIncIdGen( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "nextSecGrpIncIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_secgrpincidgen("
			+		"?" + " ) as NextSecGrpIncIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextSecGrpIncIdGen == null ) {
				stmtSelectNextSecGrpIncIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextSecGrpIncIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextSecGrpIncIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextSecGrpIncIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_secgrpincidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextSecGrpIncIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecClusterPKey pkey = schema.getFactoryCluster().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextSecGrpIncIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextSecGrpMembIdGen( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "nextSecGrpMembIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_secgrpmembidgen("
			+		"?" + " ) as NextSecGrpMembIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextSecGrpMembIdGen == null ) {
				stmtSelectNextSecGrpMembIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextSecGrpMembIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextSecGrpMembIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextSecGrpMembIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_secgrpmembidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextSecGrpMembIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecClusterPKey pkey = schema.getFactoryCluster().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextSecGrpMembIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextServiceIdGen( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "nextServiceIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_serviceidgen("
			+		"?" + " ) as NextServiceIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextServiceIdGen == null ) {
				stmtSelectNextServiceIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextServiceIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextServiceIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextServiceIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_serviceidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextServiceIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecClusterPKey pkey = schema.getFactoryCluster().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextServiceIdGen( Authorization, pkey );
		return( retval );
	}

	public void createCluster( CFSecAuthorization Authorization,
		CFSecClusterBuff Buff )
	{
		final String S_ProcName = "createCluster";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			String FullDomName = Buff.getRequiredFullDomName();
			String Description = Buff.getRequiredDescription();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_clus( ?, ?, ?, ?, ?, ?" + ", "
				+		"?" + ", "
				+		"?" + " )";
			if( stmtCreateByPKey == null ) {
				stmtCreateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtCreateByPKey.setString( argIdx++, "a001" );
			stmtCreateByPKey.setString( argIdx++, FullDomName );
			stmtCreateByPKey.setString( argIdx++, Description );
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecClusterBuff createdBuff = unpackClusterResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredId( createdBuff.getRequiredId() );
				Buff.setRequiredFullDomName( createdBuff.getRequiredFullDomName() );
				Buff.setRequiredDescription( createdBuff.getRequiredDescription() );
				Buff.setRequiredRevision( createdBuff.getRequiredRevision() );
				Buff.setCreatedByUserId( createdBuff.getCreatedByUserId() );
				Buff.setCreatedAt( createdBuff.getCreatedAt() );
				Buff.setUpdatedByUserId( createdBuff.getUpdatedByUserId() );
				Buff.setUpdatedAt( createdBuff.getUpdatedAt() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public String S_sqlSelectClusterDistinctClassCode = null;

	public String getSqlSelectClusterDistinctClassCode() {
		if( S_sqlSelectClusterDistinctClassCode == null ) {
			S_sqlSelectClusterDistinctClassCode =
					"SELECT "
					+		"DISTINCT a001.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".clus AS a001 ";
		}
		return( S_sqlSelectClusterDistinctClassCode );
	}

	public String S_sqlSelectClusterBuff = null;

	public String getSqlSelectClusterBuff() {
		if( S_sqlSelectClusterBuff == null ) {
			S_sqlSelectClusterBuff =
					"SELECT "
					+		"a001.Id, "
					+		"a001.FullDomName, "
					+		"a001.Description, "
					+		"a001.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".clus AS a001 ";
		}
		return( S_sqlSelectClusterBuff );
	}

	protected CFSecClusterBuff unpackClusterResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackClusterResultSetToBuff";
		int idxcol = 1;
		CFSecClusterBuff buff = schema.getFactoryCluster().newBuff();
		{
			String colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedByUserId( null );
			}
			else {
				buff.setCreatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedAt( null );
			}
			else {
				buff.setCreatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedByUserId( null );
			}
			else {
				buff.setUpdatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedAt( null );
			}
			else {
				buff.setUpdatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
		}
		buff.setRequiredId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredFullDomName( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredDescription( resultSet.getString( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFSecClusterBuff readDerived( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecClusterBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecClusterBuff lockDerived( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecClusterBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecClusterBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFSecClusterBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFSecClusterBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlClusterTable.readDerivedByIdIdx() ";
		CFSecClusterBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByIdIdx( Authorization,
				Id );
		return( buff );
	}

	public CFSecClusterBuff readDerivedByUDomNameIdx( CFSecAuthorization Authorization,
		String FullDomName )
	{
		final String S_ProcName = "CFBamPgSqlClusterTable.readDerivedByUDomNameIdx() ";
		CFSecClusterBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUDomNameIdx( Authorization,
				FullDomName );
		return( buff );
	}

	public CFSecClusterBuff readDerivedByUDescrIdx( CFSecAuthorization Authorization,
		String Description )
	{
		final String S_ProcName = "CFBamPgSqlClusterTable.readDerivedByUDescrIdx() ";
		CFSecClusterBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUDescrIdx( Authorization,
				Description );
		return( buff );
	}

	public CFSecClusterBuff readBuff( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "readBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clus( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByPKey == null ) {
				stmtReadBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecClusterBuff buff = unpackClusterResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecClusterBuff lockBuff( CFSecAuthorization Authorization,
		CFSecClusterPKey PKey )
	{
		final String S_ProcName = "lockBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_clus( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtLockBuffByPKey == null ) {
				stmtLockBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtLockBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecClusterBuff buff = unpackClusterResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecClusterBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clus_all( ?, ?, ?, ?, ? )";
			if( stmtReadAllBuff == null ) {
				stmtReadAllBuff = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			resultSet = stmtReadAllBuff.executeQuery();
			List<CFSecClusterBuff> buffList = new LinkedList<CFSecClusterBuff>();
			while( resultSet.next() ) {
				CFSecClusterBuff buff = unpackClusterResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecClusterBuff[] retBuff = new CFSecClusterBuff[ buffList.size() ];
			Iterator<CFSecClusterBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	/**
	 *	Read a page of all the specific Cluster buffer instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific Cluster instances in the database accessible for the Authorization.
	 */
	public CFSecClusterBuff[] pageAllBuff( CFSecAuthorization Authorization,
		Long priorId )
	{
		final String S_ProcName = "pageAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_clus_all( ?, ?, ?, ?, ?, ? )";
			if( stmtPageBuffAll == null ) {
				stmtPageBuffAll = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffAll.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffAll.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( priorId != null ) {
				stmtPageBuffAll.setLong( argIdx++, priorId.longValue() );
			}
			else {
				stmtPageBuffAll.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffAll.executeQuery();
			List<CFSecClusterBuff> buffList = new LinkedList<CFSecClusterBuff>();
			while( resultSet.next() ) {
				CFSecClusterBuff buff = unpackClusterResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecClusterBuff[] retBuff = new CFSecClusterBuff[ buffList.size() ];
			Iterator<CFSecClusterBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecClusterBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clus_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByIdIdx == null ) {
				stmtReadBuffByIdIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecClusterBuff buff = unpackClusterResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecClusterBuff readBuffByUDomNameIdx( CFSecAuthorization Authorization,
		String FullDomName )
	{
		final String S_ProcName = "readBuffByUDomNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clus_by_udomnameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUDomNameIdx == null ) {
				stmtReadBuffByUDomNameIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUDomNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUDomNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUDomNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUDomNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUDomNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUDomNameIdx.setString( argIdx++, FullDomName );
			resultSet = stmtReadBuffByUDomNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecClusterBuff buff = unpackClusterResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecClusterBuff readBuffByUDescrIdx( CFSecAuthorization Authorization,
		String Description )
	{
		final String S_ProcName = "readBuffByUDescrIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clus_by_udescridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUDescrIdx == null ) {
				stmtReadBuffByUDescrIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUDescrIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUDescrIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUDescrIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUDescrIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUDescrIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUDescrIdx.setString( argIdx++, Description );
			resultSet = stmtReadBuffByUDescrIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecClusterBuff buff = unpackClusterResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void updateCluster( CFSecAuthorization Authorization,
		CFSecClusterBuff Buff )
	{
		final String S_ProcName = "updateCluster";
		ResultSet resultSet = null;
		try {
			long Id = Buff.getRequiredId();
			String FullDomName = Buff.getRequiredFullDomName();
			String Description = Buff.getRequiredDescription();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_clus( ?, ?, ?, ?, ?, ?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + " )";
			if( stmtUpdateByPKey == null ) {
				stmtUpdateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtUpdateByPKey.setString( argIdx++, "a001" );
			stmtUpdateByPKey.setLong( argIdx++, Id );
			stmtUpdateByPKey.setString( argIdx++, FullDomName );
			stmtUpdateByPKey.setString( argIdx++, Description );
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecClusterBuff updatedBuff = unpackClusterResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredFullDomName( updatedBuff.getRequiredFullDomName() );
				Buff.setRequiredDescription( updatedBuff.getRequiredDescription() );
				Buff.setRequiredRevision( updatedBuff.getRequiredRevision() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteCluster( CFSecAuthorization Authorization,
		CFSecClusterBuff Buff )
	{
		final String S_ProcName = "deleteCluster";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clus( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
			if( stmtDeleteByPKey == null ) {
				stmtDeleteByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtDeleteByPKey.setLong( argIdx++, Id );
			stmtDeleteByPKey.setInt( argIdx++, Buff.getRequiredRevision() );;
			resultSet = stmtDeleteByPKey.executeQuery();
			if( resultSet.next() ) {
				boolean deleteFlag = resultSet.getBoolean( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 record result set to be returned by delete, not 0 rows" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteClusterByIdIdx( CFSecAuthorization Authorization,
		long argId )
	{
		final String S_ProcName = "deleteClusterByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clus_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByIdIdx == null ) {
					stmtDeleteByIdIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByIdIdx.setLong( argIdx++, argId );
				resultSet = stmtDeleteByIdIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
			}
			catch( SQLException e ) {
				throw new CFLibDbException( getClass(),
					S_ProcName,
					e );
			}
			finally {
				if( resultSet != null ) {
					try {
						resultSet.close();
					}
					catch( SQLException e ) {
					}
					resultSet = null;
				}
			}
	}

	public void deleteClusterByIdIdx( CFSecAuthorization Authorization,
		CFSecClusterPKey argKey )
	{
		deleteClusterByIdIdx( Authorization,
			argKey.getRequiredId() );
	}

	public void deleteClusterByUDomNameIdx( CFSecAuthorization Authorization,
		String argFullDomName )
	{
		final String S_ProcName = "deleteClusterByUDomNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clus_by_udomnameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUDomNameIdx == null ) {
					stmtDeleteByUDomNameIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUDomNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUDomNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUDomNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUDomNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUDomNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUDomNameIdx.setString( argIdx++, argFullDomName );
				resultSet = stmtDeleteByUDomNameIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteClusterByUDomNameIdx( CFSecAuthorization Authorization,
		CFSecClusterByUDomNameIdxKey argKey )
	{
		deleteClusterByUDomNameIdx( Authorization,
			argKey.getRequiredFullDomName() );
	}

	public void deleteClusterByUDescrIdx( CFSecAuthorization Authorization,
		String argDescription )
	{
		final String S_ProcName = "deleteClusterByUDescrIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clus_by_udescridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUDescrIdx == null ) {
					stmtDeleteByUDescrIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUDescrIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUDescrIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUDescrIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUDescrIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUDescrIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUDescrIdx.setString( argIdx++, argDescription );
				resultSet = stmtDeleteByUDescrIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteClusterByUDescrIdx( CFSecAuthorization Authorization,
		CFSecClusterByUDescrIdxKey argKey )
	{
		deleteClusterByUDescrIdx( Authorization,
			argKey.getRequiredDescription() );
	}

	/**
	 *	Release the prepared statements.
	 *	<p>
	 *	When the schema changes connections, the prepared statements
	 *	have to be released because they contain connection-specific
	 *	information for most databases.
	 */
	public void releasePreparedStatements() {
		final String S_ProcName = "releasePreparedStatements";
		S_sqlSelectClusterDistinctClassCode = null;
		S_sqlSelectClusterBuff = null;

		if( stmtReadBuffByPKey != null ) {
			try {
				stmtReadBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByPKey = null;
		}
		if( stmtReadBuffAll != null ) {
			try {
				stmtReadBuffAll.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffAll = null;
		}
		if( stmtPageBuffAll != null ) {
			try {
				stmtPageBuffAll.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffAll = null;
		}
		if( stmtLockBuffByPKey != null ) {
			try {
				stmtLockBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtLockBuffByPKey = null;
		}
		if( stmtCreateByPKey != null ) {
			try {
				stmtCreateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtCreateByPKey = null;
		}
		if( stmtUpdateByPKey != null ) {
			try {
				stmtUpdateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtUpdateByPKey = null;
		}
		if( stmtDeleteByPKey != null ) {
			try {
				stmtDeleteByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByPKey = null;
		}
		if( stmtDeleteByIdIdx != null ) {
			try {
				stmtDeleteByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByIdIdx = null;
		}
		if( stmtDeleteByUDomNameIdx != null ) {
			try {
				stmtDeleteByUDomNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUDomNameIdx = null;
		}
		if( stmtDeleteByUDescrIdx != null ) {
			try {
				stmtDeleteByUDescrIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUDescrIdx = null;
		}
		if( stmtReadAllBuff != null ) {
			try {
				stmtReadAllBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtReadAllBuff = null;
		}
		if( stmtReadBuffByIdIdx != null ) {
			try {
				stmtReadBuffByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByIdIdx = null;
		}
		if( stmtReadBuffByUDomNameIdx != null ) {
			try {
				stmtReadBuffByUDomNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUDomNameIdx = null;
		}
		if( stmtReadBuffByUDescrIdx != null ) {
			try {
				stmtReadBuffByUDescrIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUDescrIdx = null;
		}
		if( stmtSelectNextSecAppIdGen != null ) {
			try {
				stmtSelectNextSecAppIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextSecAppIdGen = null;
		}
		if( stmtSelectNextSecFormIdGen != null ) {
			try {
				stmtSelectNextSecFormIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextSecFormIdGen = null;
		}
		if( stmtSelectNextSecGroupIdGen != null ) {
			try {
				stmtSelectNextSecGroupIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextSecGroupIdGen = null;
		}
		if( stmtSelectNextHostNodeIdGen != null ) {
			try {
				stmtSelectNextHostNodeIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextHostNodeIdGen = null;
		}
		if( stmtSelectNextSecGroupFormIdGen != null ) {
			try {
				stmtSelectNextSecGroupFormIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextSecGroupFormIdGen = null;
		}
		if( stmtSelectNextSecGrpIncIdGen != null ) {
			try {
				stmtSelectNextSecGrpIncIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextSecGrpIncIdGen = null;
		}
		if( stmtSelectNextSecGrpMembIdGen != null ) {
			try {
				stmtSelectNextSecGrpMembIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextSecGrpMembIdGen = null;
		}
		if( stmtSelectNextServiceIdGen != null ) {
			try {
				stmtSelectNextServiceIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextServiceIdGen = null;
		}
	}
}
